"use client";

import { CurrentUser } from "@/app/actions/getCurrentUser";
import AccountDropdown from "./AccountDropdown";

interface Props {
  currentUser: CurrentUser;
}

export default function AdminHeader({ currentUser }: Props) {
  return (
    <header className="w-full flex items-center justify-between px-6 py-4 bg-white shadow-sm border-b">
      {/* Centered title */}
      <h1 className="text-lg font-semibold text-gray-800 mx-auto">
        RGBunny DashBoard
      </h1>

      {/* Right - Account */}
      <div className="ml-auto">
        <AccountDropdown user={currentUser} />
      </div>
    </header>
  );
}
