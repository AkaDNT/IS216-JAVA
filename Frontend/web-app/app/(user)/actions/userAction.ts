"use server";
import { fetchWrapper } from "@/lib/fetchWrapper";
import { FieldValues } from "react-hook-form";

export const updateMe = async (data: FieldValues) => {
  return await fetchWrapper.patch(`/user/me`, data);
};

export const getAllUsers = async () => {
  return await fetchWrapper.get(`/manage/get-all-users`);
};

export const getAllCustomers = async () => {
  return await fetchWrapper.get(`/manage/get-all-customers`);
};

export const getAllEmployees = async () => {
  return await fetchWrapper.get(`/manage/get-all-employees`);
};
