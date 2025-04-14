# Dockerfile
# Estágio de construção
FROM node:20-alpine AS builder

# Instala o pnpm via corepack
RUN corepack enable && corepack prepare pnpm@latest --activate

WORKDIR /app

# Copia arquivos de gerenciamento de dependências
COPY package.json pnpm-lock.yaml* pnpm-workspace.yaml* ./

# Instala dependências
RUN pnpm install --frozen-lockfile

# Copia o restante dos arquivos
COPY . .

# Constrói a aplicação
RUN pnpm run build

# Estágio de produção
FROM node:20-alpine AS runner
WORKDIR /app

ENV NODE_ENV production

# Instala o pnpm no estágio final
RUN corepack enable && corepack prepare pnpm@latest --activate

# Copia os arquivos necessários
COPY --from=builder /app/package.json ./
COPY --from=builder /app/pnpm-lock.yaml ./
COPY --from=builder /app/next.config.ts ./
COPY --from=builder /app/postcss.config.mjs ./
COPY --from=builder /app/public ./public
COPY --from=builder /app/src ./src
COPY --from=builder /app/.next ./.next
COPY --from=builder /app/node_modules ./node_modules

# Expõe a porta
EXPOSE 3000

# Comando de inicialização
CMD ["pnpm", "start"]
