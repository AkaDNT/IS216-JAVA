"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";
import { cn } from "@/lib/utils";

const navItems = [
  { label: "Profile Information", href: "/account/profile" },
  { label: "Address Information", href: "/account/addresses" },
  { label: "Change Password", href: "/account/reset-password" },
];

export default function AccountSidebar() {
  const pathname = usePathname();

  return (
    <aside className="w-64 bg-white border-r border-gray-200 rounded-l-2xl p-6">
      <h2 className="text-2xl font-semibold text-rose-600 mb-6">
        Account Center
      </h2>
      <nav className="flex flex-col space-y-2">
        {navItems.map((item) => (
          <Link
            key={item.href}
            href={item.href}
            className={cn(
              "px-4 py-2 rounded-lg text-base font-medium transition-colors",
              pathname === item.href
                ? "bg-rose-600 text-white"
                : "text-gray-700 hover:bg-rose-100 hover:text-rose-700"
            )}
          >
            {item.label}
          </Link>
        ))}
      </nav>
    </aside>
  );
}
