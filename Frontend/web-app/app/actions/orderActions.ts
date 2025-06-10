import { fetchWrapperClient } from "@/lib/fetchWrapperClient";
import { FieldValues } from "react-hook-form";

export const orderItemsInCart = async (
  data: FieldValues,
  paymentMethod: string
) => {
  const jwtToken = localStorage.getItem("jwtToken");
  return await fetchWrapperClient.post(
    `/order/users/payments/${paymentMethod}`,
    data,
    jwtToken ?? undefined
  );
};
