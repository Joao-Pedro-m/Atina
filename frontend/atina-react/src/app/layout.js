import { Inter } from "next/font/google";
import "./globals.css";

export const inter = Inter({subsets: ["latin"]});

export default function RootLayout({ children }) {
  return (
    <html lang="pt-br">
      <body className={`${inter.variable}`}>{children}</body>
    </html>
  );
}