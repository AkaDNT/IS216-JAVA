"use server";
import { getAllCustomers } from "@/app/(user)/actions/userAction";
import UserTable from "@/app/components/ui/UserTable";
import React, { Suspense } from "react";

export default async function UsersPage() {
  const users = await getAllCustomers();

  return (
    <section className="p-6">
      <div className="flex items-center justify-between mb-6">
        <h1 className="text-2xl font-bold">All Customers</h1>
      </div>

      <Suspense fallback={<p>Loading users...</p>}>
        <UserTable users={users} />
      </Suspense>
    </section>
  );
}
