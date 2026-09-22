# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
# 開發伺服器（WasmJS，主要目標）
./gradlew wasmJsBrowserDevelopmentRun

# 開發伺服器（JS 目標）
./gradlew jsBrowserDevelopmentRun

# 執行測試
./gradlew wasmJsTest

# 正式建置（部署用）
./gradlew wasmJsBrowserDistribution
```

建置產物輸出至 `composeApp/build/dist/wasmJs/productionExecutable/`，再與 `assets/` 合併後部署到 GitHub Pages。

## Astro 網站（website/）

`website/` 是以 **Astro 4 + Tailwind CSS v3** 重寫的 SEO 友好版本，為主要對外網站。

```bash
# 開發伺服器
cd website && npm install && npx astro dev   # http://localhost:4321

# 正式建置
cd website && npx astro build
```

### 架構

```
website/src/
  layouts/Layout.astro          ← 頁面骨架、JSON-LD、主題初始化
  pages/index.astro             ← 組合所有 Section，<main class="min-h-screen">（不可加 items-center，會壓縮子元素寬度）
  components/                   ← 各 Section 元件
  data/resume.ts                ← 所有靜態資料（profile、skills、projects 等）
  styles/global.css             ← CSS 自訂屬性（明/暗主題色）、.card、.skill-bar-fill 動畫
```

### 重要細節

- **主題**：`data-theme="dark"` 掛在 `<html>`，預設深色，`localStorage` 記憶使用者選擇。
- **色彩系統**：Material Design 3 色票對應到 CSS 變數（`--color-primary` 等），Tailwind 以 `var()` 引用，定義於 `tailwind.config.mjs`。
- **版面寬度**：最大內容寬度 840px，Tailwind 自訂 `max-w-content: '840px'`。每個 Section 外層用 `px-4`，內層 div 用 `mx-auto w-full max-w-content` 置中——**不可在外層加 `flex justify-center` 或 `items-center`，否則會讓子元素縮成內容寬度。**
- **聯絡表單**：前端驗證已完成，Supabase 送出邏輯尚未接入（待補）。
- **nav active 狀態**：TopBar 以 IntersectionObserver 追蹤目前區塊，頁面初始預設 highlight `#about`。

## 架構

這是一個 **Kotlin Multiplatform + Compose Multiplatform** 個人履歷網站，支援兩個 Web 目標：`wasmJs`（主要）與 `js`。兩者程式碼幾乎完全相同、分別放在各自的 source set 中，沒有共用的 `commonMain` Kotlin 程式碼。

### 目錄結構

```
composeApp/src/
  wasmJsMain/kotlin/com/jarvislin/resume/   ← 主要開發目標
  jsMain/kotlin/com/jarvislin/resume/       ← JS 備用目標（結構相同）
```

### 資料流

```
ResumeData.kt / 硬編碼資料（Profile、Skill、Achievement、Project、Reference）
       ↓
MainScreen.kt（LazyColumn，各 Section 以 item{} 組成）
       ↓
各 Section Composable（ProfileSection、SkillSection、PortfolioSection 等）

ContactSection（聯絡表單）
  → MainViewModel（狀態管理）
  → ContactRepository（Supabase Auth + Edge Function）
```

### 依賴注入

使用 **Koin**，定義於三個模組：
- `dataModule`：提供 `SupabaseClient`（已含 hardcoded URL 與 anon key）
- `repositoryModule`：提供 `ContactRepository`
- `viewModelModule`：提供 `MainViewModel`

### 重要細節

- **響應式佈局**：`MainScreen` 以 `screenWidth < 768dp` 判斷是否使用手機版排版，並將 `useMobileLayout: Boolean` 向下傳遞給各 Section。
- **資料靜態化**：Profile、Skills、Projects、Achievements、References 等內容全部硬編碼在 `data/` 下的各資料類別，Work Experience 直接寫在 `MainScreen.kt`。
- **主題切換**：`MainViewModel.switchTheme()` 切換明暗模式，色彩定義在 `ui/Color.kt` 與 `ui/Theme.kt`。
- **聯絡表單後端**：透過 `signInAnonymously()` 取得 Supabase 匿名 Auth token，再呼叫名為 `store-message` 的 Edge Function。
- **測試框架**：`dev.mokkery` 用於 mock，測試檔位於 `wasmJsTest/`。
- **資源同步**：`jsMain/composeResources/drawable/` 與 `wasmJsMain/composeResources/drawable/` 內容完全相同，新增圖片時須同步兩邊。
