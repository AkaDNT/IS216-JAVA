"use client";

import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  Tooltip,
  CartesianGrid,
} from "recharts";
import { Order } from "../models/Order";

export default function RevenueByDateChart({ orders }: { orders: Order[] }) {
  const revenueMap: Record<string, number> = {};

  orders.forEach((order) => {
    const dateKey = new Date(order.orderDate).toISOString().slice(0, 10);
    revenueMap[dateKey] = (revenueMap[dateKey] || 0) + order.totalAmount;
  });

  const chartData = Object.entries(revenueMap).map(([date, total]) => ({
    date,
    total,
  }));

  return (
    <div>
      <h2 className="text-xl font-semibold mb-2">Revenue by Date</h2>
      <LineChart width={600} height={300} data={chartData}>
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="date" />
        <YAxis />
        <Tooltip />
        <Line type="monotone" dataKey="total" stroke="#8884d8" />
      </LineChart>
    </div>
  );
}
