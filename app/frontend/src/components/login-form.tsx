import React, { useState } from 'react';
import { redirect } from 'next/navigation';

const Login: React.FC = () => {
    const [name, setName] = useState("");
    const [pwd, setPwd] = useState("");
    
    async function getInfo() {
      redirect('/storage');
    }

    return (
    <div className={`bg-white flex flex-col justify-center items-center h-full px-[20%]`}>
      <label className="self-start">
        Nome:
        <input className="border border-[1px] border-ridge rounded-full text-xl p-2.5 my-[5%]" value={name} onChange={e => setName(e.target.value)} />
      </label>
      <label className="self-start">
        Senha:
        <input className="border border-[1px] border-ridge rounded-full text-xl p-2.5 my-[5%]" type="password" value={pwd} onChange={e => setPwd(e.target.value)} />
      </label>
        <button className="self-auto rounded-full bg-[#A9A9A9] text-xl mt-[10%] px-4 py-2 transition duration-500hover:bg-[#bdbdbd] hover:shadow-[0_0_5px_2px_black]active:bg-[#3a3a3a] active:text-white active:shadow-[inset_0_2px_1px_3px_black]" 
        onClick={getInfo}>ENTRAR</button>
    </div>
)};

export default Login;