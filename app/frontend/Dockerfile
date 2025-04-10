# 1 - Estágio de construção com Node 18
FROM node:18-alpine AS development

# 2 - Definir diretório de trabalho
WORKDIR /app

# 3 - Copiar arquivos de configuração
COPY package.json pnpm-lock.yaml* ./
COPY tsconfig.json ./
COPY next.config.ts ./
COPY postcss.config.mjs ./

# 4 - Instalar pnpm
RUN corepack enable && corepack prepare pnpm@latest --activate

# 5 - Instalar dependências (incluindo devDependencies)
RUN pnpm install

# 6 - Copiar o restante dos arquivos
COPY . .

# 7 - Expor a porta 3000
EXPOSE 3000

# 8 - Iniciar o servidor de desenvolvimento
CMD ["pnpm", "dev"]
