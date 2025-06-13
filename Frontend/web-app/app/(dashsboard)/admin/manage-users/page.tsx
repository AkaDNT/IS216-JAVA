"use server";
import { getAllUsers } from "@/app/(user)/actions/userAction";
import UserTable from "@/app/components/ui/UserTable";
import React, { Suspense } from "react";

export default async function UsersPage() {
  const users = await getAllUsers();

  return (
    <section className="p-6">
      <div className="flex items-center justify-between mb-6">
        <h1 className="text-2xl font-bold">All Users</h1>
      </div>

      <Suspense fallback={<p>Loading users...</p>}>
        <UserTable users={users} />
      </Suspense>
    </section>
  );
}
