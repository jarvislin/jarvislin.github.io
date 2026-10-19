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
  pages/index.astro, pages/zh/index.astro ← 英文（/）與中文（/zh/）頁面，都只渲染 components/HomePage.astro 並傳入 lang
  data/resume.ts                ← 所有靜態資料（profile、skills、projects 等）；每個可翻譯項目的中文放在同一項目的 `zh` 欄位，缺少的欄位退回英文
  i18n/ui.ts                    ← 介面文字的英中對照表、語言路徑與網址
  lib/icons.ts                  ← 共用 SVG 圖示路徑
  styles/global.css             ← CSS 自訂屬性（明/暗主題色）、.card、.reveal、.skill-bar-fill 動畫
```

## 重要細節

- **主題**：`data-theme` 掛在 `<html>`，由 Layout `<head>` 的 inline script 在繪製前決定：`localStorage` 的選擇優先，否則跟隨系統 `prefers-color-scheme`（無法判斷時深色）。
- **互動樣式**：`.tap` 把點擊區擴到 48px（視覺大小不變）、`.state` 是 MD3 state layer（hover 8% / focus、pressed 12%），定義於 `global.css`；新增按鈕、連結時套用，不要用 `hover:opacity-*` 或換色代替。
- **色彩系統**：Material Design 3 色票對應到 CSS 變數（`--color-primary` 等），Tailwind 以 `var()` 引用，定義於 `tailwind.config.mjs`。
- **版面寬度**：最大內容寬度 840px，Tailwind 自訂 `max-w-content: '840px'`。每個 Section 外層用 `px-4`，內層 div 用 `mx-auto w-full max-w-content` 置中——**不可在外層加 `flex justify-center` 或 `items-center`，否則會讓子元素縮成內容寬度。**
- **Scroll reveal**：加上 `.reveal` class 的元素會在進入視窗時淡入（Layout.astro 內單一 IntersectionObserver），並尊重 `prefers-reduced-motion`。
- **Projects**：分頁 Featured（預設）/ All / Work / Side Hustle；`resume.ts` 中 `featured: true` 的專案才會出現在 Featured。
- **Work Experience**：預設只顯示最近 4 筆，其餘由「Show full experience」展開。
- **多語言**：英文 `/`（預設）、繁體中文 `/zh/`，兩頁共用同一份版型，元件接收 `lang` prop，資料用 `localize(item, lang)` 取中文。語言切換是 TopBar 的一般連結（保留 hash），點擊後把選擇存到 `localStorage.lang`；Layout 的 inline script 會在 `/` 且已選中文時導向 `/zh/`。新增專案、經歷等資料時要同時補上 `zh`。
- **聯絡表單**：`ContactSection.astro` 已掛在 `index.astro`，前端驗證後以 `fetch` POST 到 Web3Forms（無後端）。`ACCESS_KEY` 常數是 web3forms.com 的公開金鑰，收件信箱由該 key 決定（要換信箱就到 Web3Forms 重新申請）。
- **nav active 狀態**：TopBar 以 IntersectionObserver 追蹤目前區塊，頁面初始預設 highlight `#about`。
- **canonical / OG**：Layout.astro 的 canonical、og:url、og:image 與 JSON-LD 使用自訂網域 `https://resume.jarvislin.com/`。
