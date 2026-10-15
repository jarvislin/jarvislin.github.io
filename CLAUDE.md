# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 專案

個人履歷網站，以 **Astro 4 + Tailwind CSS v3** 建置，原始碼在 `website/`。舊的 Kotlin Multiplatform / Compose 版本已移除（可從 git 歷史找回）。

## Commands

```bash
# 開發伺服器
cd website && npm install && npx astro dev   # http://localhost:4321

# 正式建置（輸出 website/dist）
cd website && npx astro build
```

## 部署

push 到 `master` 會觸發 `.github/workflows/deploy.yml`：在 `website/` 執行 `npm ci` + `npm run build`，再用 `peaceiris/actions-gh-pages` 將 `website/dist` 發佈到 `gh-pages` 分支（GitHub Pages）。

## 架構

```
website/src/
  layouts/Layout.astro          ← 頁面骨架、meta/OG、JSON-LD、主題初始化、scroll-reveal observer
  pages/index.astro             ← 組合所有 Section，<main class="min-h-screen">（不可加 items-center，會壓縮子元素寬度）
  components/                   ← 各 Section 元件（含 Footer.astro）
  data/resume.ts                ← 所有靜態資料（profile、skills、projects 等）
  lib/icons.ts                  ← 共用 SVG 圖示路徑
  styles/global.css             ← CSS 自訂屬性（明/暗主題色）、.card、.reveal、.skill-bar-fill 動畫
```

## 重要細節

- **主題**：`data-theme="dark"` 掛在 `<html>`，預設深色，`localStorage` 記憶使用者選擇。
- **色彩系統**：Material Design 3 色票對應到 CSS 變數（`--color-primary` 等），Tailwind 以 `var()` 引用，定義於 `tailwind.config.mjs`。
- **版面寬度**：最大內容寬度 840px，Tailwind 自訂 `max-w-content: '840px'`。每個 Section 外層用 `px-4`，內層 div 用 `mx-auto w-full max-w-content` 置中——**不可在外層加 `flex justify-center` 或 `items-center`，否則會讓子元素縮成內容寬度。**
- **Scroll reveal**：加上 `.reveal` class 的元素會在進入視窗時淡入（Layout.astro 內單一 IntersectionObserver），並尊重 `prefers-reduced-motion`。
- **Projects**：分頁 Featured（預設）/ All / Work / Side Hustle；`resume.ts` 中 `featured: true` 的專案才會出現在 Featured。
- **Work Experience**：預設只顯示最近 4 筆，其餘由「Show full experience」展開。
- **聯絡表單**：`ContactSection.astro` 目前未掛在 `index.astro`；前端驗證已完成，Supabase 送出邏輯尚未接入。
- **nav active 狀態**：TopBar 以 IntersectionObserver 追蹤目前區塊，頁面初始預設 highlight `#about`。
- **canonical / OG**：Layout.astro 的 canonical 與 og:image 使用 `https://jarvislin.github.io/`。
