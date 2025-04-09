'use client'

import Image from "next/image";
import Link from "next/link";
import styles from "@/app/assets/button.module.css";
import { inter } from "@/app/layout";
import { useState } from "react";

export default function Home() {
  const [name, setName] = useState("");
  const [pwd, setPwd] = useState("");

  async function getinfo() {
    // PEGAR O NOME E A SENHA NA API E VERIFICAR COM OS INPUTS
    // SE ESTIVER CORRETO, REDIRECIONA PARA O ESTOQUE DO USUARIO
    // SE TIVE ERRADO APARECE UM ALERT
    window.alert("Teste");
  }

  return (
    <div className={`${inter.className} bg-white flex flex-col justify-center items-center h-full px-[20%]`}>
      <label className="self-start">
        Nome:
        <input className="border border-[1px] border-ridge rounded-full text-xl p-2.5 my-[5%]" value={name} onChange={e => setName(e.target.value)} />
      </label>
      <label className="self-start">
        Senha:
        <input className="border border-[1px] border-ridge rounded-full text-xl p-2.5 my-[5%]" type="password" value={pwd} onChange={e => setPwd(e.target.value)} />
      </label>
        <button className={styles.login} onClick={getinfo}>ENTRAR</button>
    </div>
  );
}
