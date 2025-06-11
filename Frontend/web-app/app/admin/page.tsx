"use server";

import { getAllOrders } from "@/app/actions/orderActions";
import RevenueByDateChart from "./RevenueByDateChart";

export default async function StatisticsPage() {
  const orders = await getAllOrders();
  return (
    <div className="grid grid-cols-12 gap-6 p-6">
      {/* Top stats row */}
      <div className="col-span-3 bg-white p-4 rounded-xl shadow">
        Available Dish
        <br />
        150.00
      </div>
      <div className="col-span-3 bg-white p-4 rounded-xl shadow">
        Total Order
        <br />
        11,000
      </div>
      <div className="col-span-3 bg-white p-4 rounded-xl shadow">
        Total Sale
        <br />
        2,770.00
      </div>
      <div className="col-span-3 bg-white p-4 rounded-xl shadow">
        Total Profit
        <br />
        35,000
      </div>

      <div className="col-span-8 bg-white p-4 rounded-xl shadow">
        <RevenueByDateChart orders={orders} />
      </div>
    </div>
  );
}
