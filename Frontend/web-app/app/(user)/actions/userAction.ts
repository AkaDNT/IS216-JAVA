"use server";
import { fetchWrapper } from "@/lib/fetchWrapper";
import { FieldValues } from "react-hook-form";

export const updateMe = async (data: FieldValues) => {
  return await fetchWrapper.patch(`/user/me`, data);
};
