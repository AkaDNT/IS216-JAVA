"use server";
import { fetchWrapper } from "@/lib/fetchWrapper";
import { Book } from "../models/Book";
import { FieldValues } from "react-hook-form";

export const getAllBooks = async (): Promise<Book[]> => {
  return await fetchWrapper.get("/books");
};

export const addBook = async (data: FieldValues) => {
  return await fetchWrapper.post("/books", data);
};

export const updateBook = async (data: FieldValues, id: number) => {
  return await fetchWrapper.patch(`/books?id=${id}`, data);
};

export const getBookById = async (id: number): Promise<Book> => {
  return await fetchWrapper.get(`/books/book?Id=${id}`);
};

export const deleteBook = async (id: number) => {
  return await fetchWrapper.del(`/books?id=${id}`);
};
