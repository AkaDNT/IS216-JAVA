"use server";
import { CreditCard, Truck } from "lucide-react";
import { orderItemsInCart } from "../actions/orderActions";
import { UserAddress } from "../models/Address";
import SubmitButton from "./SubmitButton";

type Props = {
  addresses: UserAddress[];
};

const paymentMethods = [
  { label: "CARD", icon: "CreditCard" },
  { label: "COD", icon: "Truck" },
  { label: "PAYPAL", icon: "DollarSign" },
];

export default function OrderForm({ addresses }: Props) {
  const handleSubmit = async (formData: FormData) => {
    const data = {
      addressId: Number(formData.get("addressId")),
      paymentMethod: formData.get("paymentMethod") as string,
      pgName: formData.get("pgName") as string,
      pgPaymentId: formData.get("pgPaymentId") as string,
      pgStatus: formData.get("pgStatus") as string,
      pgResponseMessage: formData.get("pgResponseMessage") as string,
    };

    try {
      await orderItemsInCart(data, data.paymentMethod);
      console.log("Đặt hàng thành công!");
    } catch (err) {
      console.error("Có lỗi xảy ra:", err);
    }
  };

  return (
    <form action={handleSubmit} className="space-y-6">
      {/* Địa chỉ */}
      <div>
        <h2 className="text-lg font-semibold mb-2">Chọn địa chỉ</h2>
        {addresses.map((addr) => (
          <label
            key={addr.id}
            className="block border rounded-xl p-4 cursor-pointer has-[:checked]:border-blue-600 border-gray-300 mb-2"
          >
            <input
              type="radio"
              name="addressId"
              value={addr.id}
              required
              className="mr-3 peer"
            />
            <div className="peer-checked:font-semibold">
              <p>
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
            <label
              key={method.label}
              className="flex items-center px-4 py-2 border rounded-xl transition has-[:checked]:bg-blue-600 has-[:checked]:text-white bg-white text-gray-700 border-gray-300 cursor-pointer"
            >
              <input
                type="radio"
                name="paymentMethod"
                value={method.label}
                defaultChecked={method.label === "CARD"}
                className="hidden"
              />
              <span className="flex items-center">
                {method.label === "CARD" && (
                  <CreditCard className="w-4 h-4 inline mr-2" />
                )}
                {method.label === "COD" && (
                  <Truck className="w-4 h-4 inline mr-2" />
                )}
                {method.label === "PAYPAL" && <span className="mr-2">💸</span>}
                {method.label}
              </span>
            </label>
          ))}
        </div>
      </div>

      {/* Payment Info Inputs */}
      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
        {[
          {
            name: "pgName",
            label: "PG Name",
            placeholder: "Stripe / Paypal / VNPay...",
          },
          {
            name: "pgPaymentId",
            label: "PG Payment ID",
            placeholder: "pi_1adsnakdnads",
          },
          {
            name: "pgStatus",
            label: "PG Status",
            placeholder: "succeeded / failed",
          },
          {
            name: "pgResponseMessage",
            label: "PG Response Message",
            placeholder: "Payment successful",
          },
        ].map((field) => (
          <div key={field.name}>
            <label className="block mb-1 font-medium">{field.label}</label>
            <input
              type="text"
              name={field.name}
              required
              className="w-full border px-3 py-2 rounded-lg"
              placeholder={field.placeholder}
            />
          </div>
        ))}
      </div>

      <SubmitButton />
    </form>
  );
}
