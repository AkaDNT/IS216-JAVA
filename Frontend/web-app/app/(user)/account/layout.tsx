import AccountSidebar from "./AccountSidebar";

export default function AccountLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div className="flex justify-center w-full px-4">
      <div className="flex w-full max-w-7xl bg-white border border-gray-200 rounded-2xl shadow-md mt-20 mb-10 items-start overflow-hidden">
        <div className="hidden md:block">
          <AccountSidebar />
        </div>
        <main className="flex-1 p-6">{children}</main>
      </div>
    </div>
  );
}
