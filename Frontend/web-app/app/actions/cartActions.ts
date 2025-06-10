import { fetchWrapper} from "@/lib/fetchWrapper";
import { CartResponse } from "../models/CartResponse";

export const getTotalCartsItem = async (): Promise<number> => {
  return await fetchWrapper.get(`/carts/total-items`);
};

export const getUsersCart = async (): Promise<CartResponse> => {
  return await fetchWrapper.get("/carts/users/cart");
};
