import { fetchWrapper, handleResponse } from "@/lib/fetchWrapper";
import { CartResponse } from "../models/CartResponse";
import { cookies } from "next/headers";

export const getTotalCartsItem = async (): Promise<number> => {
  return await fetchWrapper.get(`/carts/total-items`);
};

export const getServerCart = async (): Promise<
  CartResponse | { error: unknown }
> => {
  const token = (await cookies()).get("token")?.value;

  try {
    const response = await fetch(`${process.env.API_URL}/carts/users/cart`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
    });

    const data = await handleResponse(response);
    return data;
  } catch (error) {
    return { error: { message: "Network error", details: error } };
  }
};

export const getUsersCart = async (): Promise<CartResponse> => {
  return await fetchWrapper.get("/carts/users/cart");
};
