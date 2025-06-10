"use client";

import { useState } from "react";
import { useForm } from "react-hook-form";
import { orderItemsInCart } from "../actions/orderActions";
import { useRouter } from "next/navigation";
import { toast } from "react-hot-toast";
import { UserAddress } from "../models/Address";
import { CreditCard, Truck } from "lucide-react";

type Props = {
  addresses: UserAddress[];
};

type PaymentFields = {
  pgName: string;
  pgPaymentId: string;
  pgStatus: string;
  pgResponseMessage: string;
};

const paymentMethods = [
  { label: "CARD", icon: <CreditCard className="w-4 h-4 inline mr-2" /> },
  { label: "COD", icon: <Truck className="w-4 h-4 inline mr-2" /> },
  { label: "PAYPAL", icon: <span className="mr-2">💸</span> },
];

export default function OrderForm({ addresses }: Props) {
  const [selectedId, setSelectedId] = useState<number | null>(null);
  const [paymentMethod, setPaymentMethod] = useState("CARD");
  const router = useRouter();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<PaymentFields>();

  const onSubmit = async (values: PaymentFields) => {
    if (!selectedId) {
      toast.error("Vui lòng chọn một địa chỉ giao hàng");
      return;
    }

    const data = {
      addressId: selectedId,
      paymentMethod,
      ...values,
    };

    try {
      await orderItemsInCart(data, data.paymentMethod);
      toast.success("Đặt hàng thành công!");
      router.push("/order/confirmation");
    } catch (err) {
      toast.error("Có lỗi xảy ra khi đặt hàng: " + err);
    }
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)} className="space-y-6">
      {/* Địa chỉ */}
      <div>
        <h2 className="text-lg font-semibold mb-2">Chọn địa chỉ</h2>
        {addresses.map((addr) => (
          <label
            key={addr.id}
            className={`block border rounded-xl p-4 cursor-pointer ${
              selectedId === addr.id ? "border-blue-600" : "border-gray-300"
            }`}
          >
            <input
              type="radio"
              name="address"
              value={addr.id}
              checked={selectedId === addr.id}
              onChange={() => setSelectedId(addr.id)}
              className="mr-3"
            />
            <div>
              <p className="font-semibold">
                {addr.buildingName}, {addr.street}
              </p>
              <p className="text-sm text-gray-500">
                {addr.ward}, {addr.district}, {addr.city}
              </p>
            </div>
          </label>
        ))}
      </div>

      {/* Payment Method */}
      <div>
        <h2 className="text-lg font-semibold mb-2">
          Chọn phương thức thanh toán
        </h2>
        <div className="flex gap-4">
          {paymentMethods.map((method) => (
            <button
              key={method.label}
              type="button"
              onClick={() => setPaymentMethod(method.label)}
              className={`flex items-center px-4 py-2 border rounded-xl transition ${
                paymentMethod === method.label
                  ? "bg-blue-600 text-white"
                  : "bg-white text-gray-700 border-gray-300"
              }`}
            >
              {method.icon}
              {method.label}
            </button>
          ))}
        </div>
      </div>

      {/* Payment Info Inputs */}
      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
        <div>
          <label className="block mb-1 font-medium">PG Name</label>
          <input
            type="text"
            {...register("pgName", { required: true })}
            className="w-full border px-3 py-2 rounded-lg"
            placeholder="Stripe / Paypal / VNPay..."
          />
          {errors.pgName && (
            <p className="text-red-500 text-sm mt-1">Bắt buộc</p>
          )}
        </div>
        <div>
          <label className="block mb-1 font-medium">PG Payment ID</label>
          <input
            type="text"
            {...register("pgPaymentId", { required: true })}
            className="w-full border px-3 py-2 rounded-lg"
            placeholder="pi_1adsnakdnads"
          />
        </div>
        <div>
          <label className="block mb-1 font-medium">PG Status</label>
          <input
            type="text"
            {...register("pgStatus", { required: true })}
            className="w-full border px-3 py-2 rounded-lg"
            placeholder="succeeded / failed"
          />
        </div>
        <div>
          <label className="block mb-1 font-medium">PG Response Message</label>
          <input
            type="text"
            {...register("pgResponseMessage", { required: true })}
            className="w-full border px-3 py-2 rounded-lg"
            placeholder="Payment successful"
          />
        </div>
      </div>

      <button
        type="submit"
        className="w-full bg-rose-600 text-white py-2 rounded-xl hover:bg-rose-700 transition"
      >
        Đặt hàng
      </button>
    </form>
  );
}
