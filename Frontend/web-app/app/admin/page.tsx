"use server";

import { getAllOrders } from "@/app/actions/orderActions";
import RevenueByDateChart from "./RevenueByDateChart";

export default async function StatisticsPage() {
  const orders = await getAllOrders();
  return (
    <div className="grid grid-cols-12 gap-6 p-6">
      <div className="col-span-8 bg-white p-4 rounded-xl shadow items-center">
        <RevenueByDateChart orders={orders} />
      </div>
    </div>
  );
}
