import type { Lang } from '../i18n/ui';

// Every translatable item carries its Chinese text in `zh`; fields missing from `zh` fall back to the English value.
export type L<T> = T & { zh?: Partial<T> };

export function localize<T extends object>(item: L<T>, lang: Lang): T {
  return lang === 'zh' && item.zh ? { ...item, ...item.zh } : item;
}

export const profile: L<{
  name: string;
  title: string;
  email: string;
  location: string;
  education: string;
  avatar: string;
  resumeUrl: string;
  links: L<{ label: string; url: string; icon: string }>[];
}> = {
  name: 'Jarvis Lin',
  title: 'Founder · Product Engineer',
  email: 'admin@jarvislin.com',
  location: 'Taipei, Taiwan',
  education: 'National Formosa University, Bachelor, Computer Science and Information Engineering, 2007–2011',
  avatar: '/images/avatar_16_9.webp',
  resumeUrl: 'https://drive.google.com/file/d/19rTCemHp7vG50n-AOHgcrF5jJC7Vsuii/view?usp=sharing',
  links: [
    { label: 'Homepage', url: 'https://jarvislin.com', icon: 'home', zh: { label: '個人網站' } },
    { label: 'GitHub', url: 'https://github.com/jarvislin', icon: 'github' },
    { label: 'LinkedIn', url: 'https://linkedin.com/in/jarvislin', icon: 'linkedin' },
  ],
  // TODO: add zh.name once the Chinese name is decided
  zh: {
    title: '創辦人 · 產品工程師',
    location: '台灣，台北',
    education: '國立虎尾科技大學 資訊工程系 學士，2007–2011',
  },
};

export const intro: L<{ text: string }> = {
  text: 'Founder of JINNO, building LingoWave — an AI-powered communication app for Taiwanese care families and migrant caregivers. Product engineer with 14+ years of experience shipping products from zero to launch.',
  zh: {
    text: 'JINNO 創辦人，目前打造 LingoWave——一款為台灣照護家庭與外籍看護設計的 AI 溝通 App。擁有 14 年以上經驗的產品工程師，擅長從零開始把產品做到上線。',
  },
};

export const aboutPoints: L<{ description: string; icon: string }>[] = [
  {
    description: 'Specialized in taking products from concept to launch, including defining specs and analyzing metrics.',
    icon: 'barchart',
    zh: { description: '擅長將產品從概念推進到上線，包括定義規格與分析數據。' },
  },
  {
    description: 'Thrives in ambiguity — defining scope from scratch and shipping end-to-end without a playbook.',
    icon: 'chat',
    zh: { description: '熟悉在混亂中前進——從零定義範圍，解決各種不確定後完成交付。' },
  },
  {
    description: 'Delivered 20+ app products, including Tumblr (100M+ downloads) and PKLOT App (Google Play Editor\'s Choice).',
    icon: 'category',
    zh: { description: '開發過 20 款以上的 App，包含下載破 1 億的 Tumblr 與多次入圍 Google Play 官方編輯精選的停車大聲公。' },
  },
  {
    description: 'Led a cross-border team of 18 across Taiwan and Indonesia, including engineers, QA, designers, and PMs.',
    icon: 'group',
    zh: { description: '曾帶領台灣、印尼 18 位成員的跨國團隊，包含工程師、QA、設計師與 PM。' },
  },
];

export interface SkillGroup {
  label: string;
  items: L<{ name: string; primary?: boolean }>[];
  zh?: { label: string };
}

export const skillGroups: SkillGroup[] = [
  {
    label: 'Languages',
    zh: { label: '程式語言' },
    items: [
      { name: 'Kotlin', primary: true },
      { name: 'Java', primary: true },
      { name: 'PHP' },
      { name: 'Go' },
      { name: 'Dart' },
      { name: 'Swift' },
    ],
  },
  {
    label: 'Mobile',
    zh: { label: '手機端開發' },
    items: [{ name: 'Android', primary: true }, { name: 'Compose Multiplatform', primary: true }, { name: 'iOS' }],
  },
  {
    label: 'Engineering & Delivery',
    zh: { label: '工程與交付' },
    items: [
      { name: 'Fullstack Development', zh: { name: '全端開發' } },
      { name: 'Scalable Architecture', zh: { name: '可擴展架構' } },
      { name: 'CI/CD Automation', zh: { name: 'CI/CD 自動化' } },
      { name: 'Analytics Integration', zh: { name: '數據分析整合' } },
      { name: 'Location-Based Services', zh: { name: '位置服務（LBS）' } },
    ],
  },
  {
    label: 'Leadership & Product',
    zh: { label: '領導與產品' },
    items: [
      { name: 'Cross-functional Collaboration', zh: { name: '跨部門協作' } },
      { name: 'Product Strategy', zh: { name: '產品策略' } },
      { name: 'Project Management', zh: { name: '專案管理' } },
      { name: 'UI/UX Collaboration', zh: { name: 'UI/UX 協作' } },
    ],
  },
];

export const achievements: L<{
  id: string;
  title: string;
  description: string;
  image: string;
  paragraphs: string[];
}>[] = [
  {
    id: 'appstore',
    title: 'Ahead of the AI Giants',
    description: "May 2026 — Produce Price Tracker reached #1 on the App Store's free Productivity chart, ahead of Google Gemini, ChatGPT, and Claude.",
    image: '/images/appstore_top.webp',
    paragraphs: [
      "In May 2026, Produce Price Tracker — my app showing current and historical fruit and vegetable prices from Taiwan's Council of Agriculture open data — reached #1 on the App Store's free Productivity chart.",
      "The climb followed social media exposure that drove about a million article views. It ranked above Google Gemini, ChatGPT, and Claude: a small utility built on public data, ahead of the biggest AI brands on the chart.",
    ],
    zh: {
      title: 'AI 巨頭之上',
      description: '2026 年 5 月——蔬果花卉行情站登上 App Store 免費「生產力」榜第一，領先 Google Gemini、ChatGPT 與 Claude。',
      paragraphs: [
        '2026 年 5 月，我做的蔬果花卉行情站——串接農業部開放資料，顯示蔬果的即時與歷史價格——登上 App Store 免費「生產力」排行榜第一名。',
        '這波流量來自社群平台的曝光，帶來約 100 萬次的瀏覽數。它排在 Google Gemini、ChatGPT 與 Claude 之上：一個用公開資料做成的小工具，擠到榜單上最大的幾個 AI 品牌前面。',
      ],
    },
  },
  {
    id: 'mvp',
    title: 'From Doubt to MVP',
    description: 'Dec 2024 — Named MVP at the Taipei 101 Tourism and Technology Showcase for impactful solo innovations in the tourism sector.',
    image: '/images/mvp.webp',
    paragraphs: [
      "At the Taipei 101 project showcase, I pitched my AI-powered translation tool to a room of industry leaders. Despite a technical hiccup, a tourism rep told me, \"Your solution directly addresses the challenges we face.\"",
      "Later, I was named MVP — a solo creator recognized among established teams. It affirmed that this work isn't just about tech, but real-world impact.",
    ],
    zh: {
      title: '從自我懷疑到拿下優勝',
      description: '2024 年 12 月——在台北 101 的科技交流站獲選 MVP，肯定我在觀光領域獨力完成的創新。',
      paragraphs: [
        '在台北 101 的成果發表上，我向全場的產業代表跟評審介紹我的 AI 翻譯工具。雖然現場出了點技術狀況，一位觀光業代表仍對我說：「我們產業需要像你這樣的人！」',
        '後來我獲選 MVP 優勝——以一人團隊的身份跟上市上櫃的團隊較勁、獲得肯定。這讓我確信：這份工作不只是技術，而是真實世界的影響。',
      ],
    },
  },
  {
    id: 'competition',
    title: 'A Celebration of Victory',
    description: 'Jun 2022 — As co-founder, I entered a startup competition of 141 teams, and we won first place.',
    image: '/images/competition.webp',
    paragraphs: [
      "Out of 141 teams, we won first place in Taiwan's Economic Daily News startup competition — earning front-page coverage for our proposal to modernize Indonesia's tax system with a centralized e-invoice platform.",
      "What began as a small cross-border effort became a mission-driven startup for public sector innovation. This award marked a turning point in our journey.",
    ],
    zh: {
      title: '勝利的時刻',
      description: '2022 年 6 月——身為共同創辦人，我們在 141 組團隊參加的創業之星拿下第一名。',
      paragraphs: [
        '在 141 組團隊中，我們拿下《經濟日報》創業之星企業組第一名，並登上報紙頭版——我們提出以集中式電子發票平台，協助印尼現代化稅務系統。',
        '這個從小規模跨國合作開始的計畫，後來成為一家以公共部門創新為使命的新創公司。這個獎項是我們旅程的轉捩點。',
      ],
    },
  },
  {
    id: 'top',
    title: 'Google Play Conquest',
    description: "Mar 2020 — A pharmacy mask tracker I built solo reached #1 in Google Play's free rankings in Taiwan.",
    image: '/images/top.webp',
    paragraphs: [
      "Amid Taiwan's COVID-19 mask shortage, I independently built a real-time pharmacy mask tracker.",
      'Within a month, it reached #1 on Google Play and became a vital public tool during the crisis—blending tech with real-world impact.',
    ],
    zh: {
      title: 'Google Play 登頂',
      description: '2020 年 3 月——我獨自開發的口罩資訊地圖，登上台灣 Google Play 免費排行第一名。',
      paragraphs: [
        'COVID-19 期間，台灣口罩不足，我獨立開發了即時藥局口罩地圖。',
        '一個月內它登上 Google Play 第一名，成為疫情期間重要的公共工具——把技術與真實世界的影響結合在一起。',
      ],
    },
  },
];

export type ProjectCategory = 'All' | 'Work' | 'Side';

export interface ProjectLink {
  title: string;
  type: 'GitHub' | 'PlayStore' | 'AppStore' | 'Website';
  url: string;
}

export type Project = L<{
  title: string;
  description: string;
  tags: string[];
  category: 'Work' | 'Side';
  logo: string;
  links: ProjectLink[];
  screenshots: string[];
  featured?: boolean;
}>;

export const projects: Project[] = [
  {
    title: 'LingoWave',
    featured: true,
    category: 'Work',
    logo: '/images/lingowave.webp',
    tags: ['App', 'Android', 'iOS', 'AI'],
    links: [
      { title: 'Website', type: 'Website', url: 'https://lingowave.app/' },
      { title: 'Google Play', type: 'PlayStore', url: 'https://play.google.com/store/apps/details?id=tech.jinno.lingowave' },
      { title: 'App Store', type: 'AppStore', url: 'https://apps.apple.com/us/app/lingowave/id6762041755' },
    ],
    screenshots: [],
    description: 'JINNO\'s flagship app for Taiwanese long-term care families and migrant caregivers. Messages are translated in real time across 6 languages, with shared health records and automated task reminders so nothing in daily care gets missed.',
    zh: {
      description: 'JINNO 的主力產品，為台灣長照家庭與外籍看護而設計。訊息可即時翻譯成 6 種語言，並結合共享健康紀錄與自動排程提醒，讓日常照護不再漏接。',
    },
  },
  {
    title: 'Vipassana Experiences',
    category: 'Side',
    logo: '/images/vipassana.webp',
    tags: ['Web'],
    links: [{ title: 'Website', type: 'Website', url: 'https://vipassana.titsia.la/' }],
    screenshots: [],
    description: 'After completing a 10-day Vipassana retreat myself, I built this site to help people who are still hesitating. It answers common questions and publishes first-hand stories collected through a Google Form (anonymous where requested), filterable by number of courses attended, age, and occupation. Built solo in September 2026.',
    zh: {
      title: '十日內觀｜真實經驗分享',
      description: '我自己完成十日內觀課程後，做了這個網站，幫助仍在猶豫的人。網站回答常見疑問，並公開透過 Google 表單蒐集的第一手經驗，可依參加次數、年齡與職業篩選。',
    },
  },
  {
    title: 'KMP Hub',
    category: 'Side',
    logo: '/images/kmp_hub.webp',
    tags: ['Web', 'Compose Multiplatform', 'Kotlin'],
    links: [{ title: 'GitHub', type: 'GitHub', url: 'https://github.com/KMP-Hub' }],
    screenshots: [],
    description: 'KMP Hub is a curated platform for discovering Kotlin Multiplatform libraries and tools. We aim to help developers explore the KMP ecosystem, accelerate cross-platform development, and foster community collaboration.',
    zh: {
      description: 'KMP Hub 是一個整理 Kotlin Multiplatform 函式庫與工具的平台，希望協助開發者探索 KMP 生態系、加速跨平台開發，並促進社群協作。',
    },
  },
  {
    title: 'Pulse for Hacker News',
    featured: true,
    category: 'Side',
    logo: '/images/pulse.webp',
    tags: ['App', 'Android', 'iOS', 'Compose Multiplatform', 'Kotlin'],
    links: [{ title: 'GitHub', type: 'GitHub', url: 'https://github.com/jarvislin/HackerNews-KMP' }],
    screenshots: [],
    description: 'This app is an open-source project, designed to showcase the capabilities of Kotlin Multiplatform Compose, delivering a seamless reading experience across Android and iOS platforms.',
    zh: {
      description: '這是一個開源專案，用來展示 Kotlin Multiplatform Compose 的能力，在 Android 與 iOS 上提供流暢的閱讀體驗。',
    },
  },
  {
    title: 'Jetpack',
    featured: true,
    category: 'Work',
    logo: '/images/jetpack.webp',
    tags: ['App', 'Kotlin'],
    links: [
      { title: 'GitHub', type: 'GitHub', url: 'https://github.com/wordpress-mobile/WordPress-Android' },
      { title: 'Google Play', type: 'PlayStore', url: 'https://play.google.com/store/apps/details?id=com.jetpack.android' },
    ],
    screenshots: [],
    description: 'Jetpack is an app that provides powerful tools and features to enhance WordPress sites, including site security, performance optimization, and content management.',
    zh: {
      description: 'Jetpack App 提供強大的工具來強化 WordPress 網站，包括網站安全、效能最佳化與內容管理。',
    },
  },
  {
    title: 'WordPress',
    featured: true,
    category: 'Work',
    logo: '/images/wordpress.webp',
    tags: ['App', 'Android', 'Kotlin'],
    links: [
      { title: 'GitHub', type: 'GitHub', url: 'https://github.com/wordpress-mobile/WordPress-Android' },
      { title: 'Google Play', type: 'PlayStore', url: 'https://play.google.com/store/apps/details?id=org.wordpress.android' },
    ],
    screenshots: [],
    description: 'WordPress is a mobile app that allows users to create, manage, and publish content on their WordPress blogs or websites directly from their devices.',
    zh: {
      description: 'WordPress App 讓使用者直接在手機上建立、管理並發佈 WordPress 部落格或網站的內容。',
    },
  },
  {
    title: 'Tumblr',
    featured: true,
    category: 'Work',
    logo: '/images/tumblr.webp',
    tags: ['App', 'Android', 'Kotlin'],
    links: [{ title: 'Google Play', type: 'PlayStore', url: 'https://play.google.com/store/apps/details?id=com.tumblr' }],
    screenshots: [],
    description: 'Tumblr is a microblogging and social networking app where users can post multimedia content and follow other users\' blogs. I was responsible for Feeds, the app\'s core feature.',
    zh: {
      description: 'Tumblr 是一個微網誌與社群網路 App，使用者可以發佈多媒體內容並追蹤其他人的部落格。我負責 Feeds，也就是文章相關的核心功能。',
    },
  },
  {
    title: 'BikesHere',
    category: 'Side',
    logo: '/images/bike.webp',
    tags: ['App', 'Android', 'Kotlin'],
    links: [],
    screenshots: [
      '/images/screenshot_bikeshere_1.webp',
      '/images/screenshot_bikeshere_2.webp',
      '/images/screenshot_bikeshere_3.webp',
      '/images/screenshot_bikeshere_4.webp',
      '/images/screenshot_bikeshere_5.webp',
    ],
    description: 'Provides geographic information for over 6,000 public bikes across Taiwan, including YouBike, YouBike 2.0, iBike, T-Bike, Pbike, K-bike, and more.',
    zh: {
      description: '提供全台超過 6,000 個公共自行車站的地理資訊，涵蓋 YouBike、YouBike 2.0、iBike、T-Bike、Pbike、K-bike 等。',
    },
  },
  {
    title: 'Taiwan Species Catalog',
    category: 'Side',
    logo: '/images/life.webp',
    tags: ['App', 'Android', 'Kotlin'],
    links: [],
    screenshots: ['/images/screenshot_life_1.webp', '/images/screenshot_life_2.webp', '/images/screenshot_life_3.webp'],
    description: 'A comprehensive science education app that provides detailed information on species found in Taiwan, including animals, plants, and insects.',
    zh: {
      title: '臺灣物種名錄',
      description: '一款內容完整的科普 App，提供台灣物種的詳細資訊，涵蓋動物、植物與昆蟲。',
    },
  },
  {
    title: 'Mask Map',
    featured: true,
    category: 'Side',
    logo: '/images/mask.webp',
    tags: ['App', 'Android', 'Kotlin'],
    links: [
      { title: 'GitHub', type: 'GitHub', url: 'https://github.com/jarvislin/drugstores' },
      { title: 'Google Play', type: 'PlayStore', url: 'https://play.google.com/store/apps/details?id=com.jarvislin.drugstores' },
    ],
    screenshots: [],
    description: 'When the epidemic broke out, the Taiwanese government restricted mask purchases. This app helped users find nearby pharmacies with available masks. It reached Top 1 in Free Apps on Google Play in March 2020 in Taiwan.',
    zh: {
      title: '口罩資訊地圖',
      description: '疫情爆發時，台灣政府限制口罩購買。這款 App 協助使用者找到附近還有口罩的藥局，並於 2020 年 3 月登上台灣 Google Play 免費榜第一名。',
    },
  },
  {
    title: 'Adoptly',
    category: 'Side',
    logo: '/images/adoptly.webp',
    tags: ['App', 'Android', 'Kotlin'],
    links: [],
    screenshots: ['/images/screenshot_adoptly_1.webp', '/images/screenshot_adoptly_2.webp'],
    description: 'Adoptly provides a way to adopt animals which live in shelters. It also shows information about shelters.',
    zh: {
      title: '浪浪抵家',
      description: '提供認養收容所動物的管道，也呈現收容所的相關資訊。',
    },
  },
  {
    title: 'IndoChat',
    featured: true,
    category: 'Work',
    logo: '/images/indochat.webp',
    tags: ['App', 'Android', 'Kotlin'],
    links: [{ title: 'Google Play', type: 'PlayStore', url: 'https://play.google.com/store/apps/details?id=id.co.indochat.app' }],
    screenshots: [],
    description: 'A comprehensive mobile application for the Indonesian government featuring chat, video/audio calling, social media integration, news feeds, and invoice scanning. I built the Android MVP on my own, then led the Android team and later served as product owner.',
    zh: {
      description: '為印尼政府打造的綜合行動應用，功能包括聊天、視訊／語音通話、社群媒體整合、新聞動態與發票掃描。我獨立完成 Android MVP，之後帶領 Android 團隊，並擔任產品負責人。',
    },
  },
  {
    title: 'Autopass',
    category: 'Work',
    logo: '/images/autopass.webp',
    tags: ['App', 'Android', 'Kotlin'],
    links: [{ title: 'Google Play', type: 'PlayStore', url: 'https://play.google.com/store/apps/details?id=com.pklotcorp.autopass' }],
    screenshots: [],
    description: 'A location-based platform offering a range of services for drivers, including finding parking and refueling options.',
    zh: {
      description: '以位置為基礎的平台，為駕駛提供多項服務，包括尋找停車場與加油站的支付。',
    },
  },
  {
    title: 'PKLOT App',
    featured: true,
    category: 'Work',
    logo: '/images/pklotapp.webp',
    tags: ['App', 'Android', 'Kotlin'],
    links: [{ title: 'Google Play', type: 'PlayStore', url: 'https://play.google.com/store/apps/details?id=com.alfred.parkinglot' }],
    screenshots: [],
    description: 'A parking and refueling assistant app selected as a Google Play Editor\'s Choice in Taiwan in both 2016 and 2017.',
    zh: {
      title: '停車大聲公',
      description: '停車與加油助理 App，於 2016 與 2017 年連續入選台灣 Google Play 編輯精選。',
    },
  },
  {
    title: 'Calories 100',
    category: 'Work',
    logo: '/images/calories100.webp',
    tags: ['App', 'Android', 'Kotlin'],
    links: [],
    screenshots: ['/images/screenshot_calories_1.webp', '/images/screenshot_calories_2.webp', '/images/screenshot_calories_3.webp'],
    description: 'An app for calculating calorie content in foods with social media features integrated with Facebook API.',
    zh: {
      description: '計算食物熱量的 App，並透過 Facebook API 整合社群功能。',
    },
  },
  {
    title: 'Produce Price Tracker',
    featured: true,
    category: 'Side',
    logo: '/images/crops_price_checker.webp',
    tags: ['App', 'Android', 'iOS', 'Compose Multiplatform', 'Kotlin'],
    links: [
      { title: 'Google Play', type: 'PlayStore', url: 'https://play.google.com/store/apps/details?id=com.jarvislin.producepricechecker' },
      { title: 'App Store', type: 'AppStore', url: 'https://apps.apple.com/us/app/id6661027883' },
    ],
    screenshots: [],
    description: "Shows current and historical fruit and vegetable prices from Taiwan's Council of Agriculture open data, plus consumer buying guides created with agricultural experts. After social media exposure drove about a million article views, it reached #1 on the App Store's free Productivity chart in May 2026.",
    zh: {
      title: '蔬果花卉行情站',
      description: '串接農業部開放資料，顯示蔬果的即時與歷史價格，並與農產專家合作提供消費者選購指南。社群平台曝光帶來約 100 萬次文章瀏覽後，於 2026 年 5 月登上 App Store 免費「生產力」榜第一名。',
    },
  },
  {
    title: 'Water Restriction',
    category: 'Side',
    logo: '/images/water_rectriction_info.webp',
    tags: ['App', 'Android', 'Java'],
    links: [],
    screenshots: ['/images/screenshot_water_1.webp', '/images/screenshot_water_2.webp'],
    description: "An app that fetches water restriction information from the government during Taiwan's serious drought in 2016, also showing reservoir water levels.",
    zh: {
      description: '2016 年台灣嚴重乾旱期間，從政府取得限水資訊的 App，同時顯示水庫水位。',
    },
  },
  {
    title: 'Car Finder',
    category: 'Side',
    logo: '/images/car_finder.webp',
    tags: ['App', 'Android', 'Kotlin'],
    links: [{ title: 'Google Play', type: 'PlayStore', url: 'https://play.google.com/store/apps/details?id=com.jarvislin.carfinder' }],
    screenshots: [],
    description: 'Car Finder uses GPS to detect and record your parking location with photos, plus navigation to help you find your car easily.',
    zh: {
      description: 'Car Finder 利用 GPS 偵測並記錄停車位置，可搭配照片，並提供導航，幫你輕鬆找到愛車。',
    },
  },
  {
    title: 'Baby Formula',
    category: 'Side',
    logo: '/images/baby_formula.webp',
    tags: ['App', 'Backend', 'Android', 'Java', 'PHP'],
    links: [],
    screenshots: ['/images/screenshot_babyformula_1.webp', '/images/screenshot_babyformula_2.webp', '/images/screenshot_babyformula_3.webp'],
    description: "Developed for a programming contest by Taiwan's Food and Drug Administration, using a milk formula dataset approved for sale in Taiwan.",
    zh: {
      description: '為台灣食品藥物管理署的程式競賽而開發，使用經核准在台灣販售的奶粉資料集。',
    },
  },
  {
    title: 'Small Trade',
    category: 'Work',
    logo: '/images/small_trade.webp',
    tags: ['App', 'Android', 'Java'],
    links: [],
    screenshots: ['/images/screenshot_smalltrade_1.webp', '/images/screenshot_smalltrade_2.webp'],
    description: "A C2C platform where sellers can post products online, upload pictures and videos to make their products authentic.",
    zh: {
      description: 'C2C 平台，賣家可以在線上刊登商品，並上傳照片與影片，讓商品更可信。',
    },
  },
  {
    title: 'VR for 3D',
    category: 'Work',
    logo: '/images/vr_for_3d.webp',
    tags: ['App', 'Android', 'Java'],
    links: [],
    screenshots: ['/images/screenshot_vrfor3d_1.webp', '/images/screenshot_vrfor3d_2.webp', '/images/screenshot_vrfor3d_3.webp'],
    description: "A 3D model viewer designed to showcase models created with Ortery's hardware.",
    zh: {
      description: '3D 模型檢視器，用來展示以 Ortery 光箱製作的模型。',
    },
  },
];

export type WorkExperience = L<{ duration: string; title: string; company: string; description: string }>;

export const workExperiences: WorkExperience[] = [
  {
    duration: 'Nov 2024 - Present',
    title: 'Founder & CEO',
    company: 'JINNO',
    description:
      'Defined and executed the company\'s vision, mission, and long-term strategy.\n\nBuilt and launched LingoWave, an AI-powered communication app for Taiwanese long-term care families and migrant caregivers — real-time translation in 6 languages, shared health records, and automated task reminders.\n\nPartnering with the Health Education Center at Chiayi Christian Hospital, Tzu Chi University, and NGOs.\n\nEngaged directly with enterprise clients to identify pain points and deliver custom solutions.',
    zh: {
      duration: '2024 年 11 月 – 至今',
      title: '創辦人暨執行長',
      description:
        '制定並執行公司的願景、使命與長期策略。\n\n打造並推出 LingoWave，一款為台灣長照家庭與外籍看護設計的 AI 溝通 App——支援 6 種語言即時翻譯、共享健康紀錄與自動任務提醒。\n\n與嘉義基督教醫院衛教中心、慈濟大學及 NGO 合作。\n\n直接與企業客戶接觸，找出痛點並提供客製化解決方案。',
    },
  },
  {
    duration: 'Nov 2022 - Jun 2024',
    title: 'Mobile Engineer',
    company: 'Automattic',
    description:
      'Developed and implemented new features for the Android applications of Tumblr, WordPress, and Jetpack, including improvements to user experience, performance, and security.\n\nResponsible for Tumblr\'s Feeds, the app\'s core feature.\n\nPlaced 2nd in Tumblr\'s internal hackathon; the project shipped the following April Fools\' Day as the Boop Button, drawing an enthusiastic response from users.\n\nCollaborated with cross-functional teams to troubleshoot and resolve technical and product issues.',
    zh: {
      duration: '2022 年 11 月 – 2024 年 6 月',
      title: '行動工程師',
      description:
        '為 Tumblr、WordPress 與 Jetpack 的 Android App 開發並上線新功能，包括使用者體驗、效能與安全性的改善。\n\n負責 Tumblr 的 Feeds，也就是 App 的核心功能。\n\n在 Tumblr 內部黑客松中獲得第二名；這個專案隔年愚人節以「Boop 按鈕」正式推出，獲得使用者熱烈迴響。\n\n與跨職能團隊合作，排查並解決技術與產品問題。',
    },
  },
  {
    duration: 'Dec 2021 - Nov 2022',
    title: 'Co-Founder & Technical Director',
    company: 'QTCOMM',
    description:
      'Led engineers to identify root causes and implement scalable technical fixes.\n\nStreamlined IT issue resolution across classified and unclassified systems, lowering infra complexity and cutting stack costs.\n\nRe-architected audio/video call features, replacing the previous solution and cutting its cost by over 85% — saving the company 8% of its annual expenses.\n\nSpearheaded integration with external partners, successfully connecting 3+ platforms including Tokopedia and Juiker.',
    zh: {
      duration: '2021 年 12 月 – 2022 年 11 月',
      title: '共同創辦人暨技術總監',
      description:
        '帶領工程師找出根本原因，並實施可擴展的技術修正。\n\n簡化涵蓋機密與非機密系統的 IT 問題處理流程，降低基礎架構複雜度並削減技術堆疊成本。\n\n重新設計音視訊通話功能的架構，取代原有方案，使其成本降低逾 85%——為公司節省 8% 的年度支出。\n\n擔任外部夥伴的技術窗口，成功串接 3 個以上的平台，包括 Tokopedia 與 Juiker。',
    },
  },
  {
    duration: 'Nov 2020 - Dec 2021',
    title: 'Project Technical Director',
    company: 'QTCOMM / PT. ICE Messenger Indonesia',
    description:
      'Led a cross-border team of 18 across Taiwan and Indonesia — engineers, QA, designers, PMs, and tech leads — streamlining workflows based on team feedback and improving efficiency by 40%.\n\nLed end-to-end product development, delivering mobile app updates, data encryption features, QA testing, and critical bug fixes.\n\nServed as product owner of IndoChat, responsible for all product planning and the roadmap from concept to launch.\n\nBuilt event tracking systems; iterated sign-up flow to achieve 70% conversion rate.',
    zh: {
      duration: '2020 年 11 月 – 2021 年 12 月',
      title: '專案技術總監',
      description:
        '帶領橫跨台灣與印尼、共 18 人的跨國團隊——包含工程師、QA、設計師、PM 與技術主管——依團隊回饋簡化工作流程，使效率提升 40%。\n\n主導端到端產品開發，交付行動 App 更新、資料加密功能、QA 測試與重大錯誤修復。\n\n擔任 IndoChat 的產品負責人，負責所有產品規劃，以及從概念到上線的產品路線圖。\n\n建置事件追蹤系統；反覆優化註冊流程，達成 70% 的轉換率。',
    },
  },
  {
    duration: 'Jun 2019 - Nov 2020',
    title: 'Android Team Leader',
    company: 'QTCOMM / PT. ICE Messenger Indonesia',
    description:
      'Independently built the IndoChat Android MVP as the sole developer, then continued development with teammates as Team Leader.\n\nLed Android engineers to deliver features across multiple apps, refining architecture and product design based on evolving specs.\n\nOversaw end-to-end development of 3+ Android apps—from research and planning to launch and post-release support.\n\nBuilt CI/CD pipelines to automate testing and deployment, saving over 50 minutes of manual work per build.',
    zh: {
      duration: '2019 年 6 月 – 2020 年 11 月',
      title: 'Android 團隊主管',
      description:
        '以唯一開發者的身分獨立完成 IndoChat 的 Android MVP，之後以團隊主管的身分與同事一起持續開發。\n\n帶領 Android 工程師為多款 App 交付功能，並依需求變化調整架構與產品設計。\n\n負責 3 款以上 Android App 的端到端開發——從研究、規劃到上線與上線後支援。\n\n建置 CI/CD 流程，自動化測試與部署，每次建置省下 50 分鐘以上的人工作業。',
    },
  },
  {
    duration: 'Jan 2018 - May 2019',
    title: 'Android Engineer',
    company: 'Autopass',
    description:
      'Developed a location-based Android application with robust architectures and complex user interfaces for parking, gas stations, and driver-related services.\n\nProvided code maintenance and system upgrades to maximize performance.',
    zh: {
      duration: '2018 年 1 月 – 2019 年 5 月',
      title: 'Android 工程師',
      description:
        '開發以地理位置為基礎的 Android App，具備穩健的架構與複雜的使用者介面，服務範圍包含停車場、加油站與駕駛相關服務。\n\n負責程式碼維護與系統升級，以發揮最佳效能。',
    },
  },
  {
    duration: 'Apr 2016 - Dec 2017',
    title: 'Android Engineer',
    company: 'PKLOT (Acquired by Acer)',
    description:
      'Designed and implemented services, applications, and frameworks to power core tech integrations.\n\nBuilt software for license plate recognition, enabling seamless fuel payment matching at gas stations.',
    zh: {
      duration: '2016 年 4 月 – 2017 年 12 月',
      title: 'Android 工程師',
      company: '停車大聲公（已被宏碁收購）',
      description:
        '設計並實作服務、應用程式與框架，支撐核心技術整合。\n\n開發車牌辨識軟體，讓加油站能順暢完成油費付款比對。',
    },
  },
  {
    duration: 'Apr 2015 - Jan 2016',
    title: 'Android Engineer',
    company: 'iFit',
    description:
      'Built advanced fitness applications and SDK for the Android platform, launching new features to create health and fitness experiences.\n\nPrototyped innovative features using the newest APIs; tested for robustness and general reliability.',
    zh: {
      duration: '2015 年 4 月 – 2016 年 1 月',
      title: 'Android 工程師',
      description:
        '為 Android 平台打造進階的健身 App 與 SDK，推出新功能，創造健康與健身體驗。\n\n使用最新的 API 為創新功能製作原型，並測試其穩定性與整體可靠度。',
    },
  },
  {
    duration: 'Oct 2014 - Nov 2014',
    title: 'Android Engineer',
    company: 'Zinwell',
    description:
      "Built a proof of concept for Zinwell's e-commerce app, Small Trade.",
    zh: {
      duration: '2014 年 10 月 – 2014 年 11 月',
      title: 'Android 工程師',
      company: '兆赫電子',
      description: '為兆赫電子內部創業的電商 App「小買賣」建立概念驗證。',
    },
  },
  {
    duration: 'Jun 2012 - Jul 2014',
    title: 'Software Engineer',
    company: 'Ortery Technologies, Inc',
    description:
      'Participated in the development of mobile apps and websites.\n\nContributed to the implementation of Scen3D and SaaSPhoto, focusing on front-end and integration tasks.',
    zh: {
      duration: '2012 年 6 月 – 2014 年 7 月',
      title: '軟體工程師',
      company: '萬里科技',
      description:
        '參與行動 App 與網站的開發。\n\n參與 Scen3D 與 SaaSPhoto 的實作，負責前端與整合工作。',
    },
  },
];

export const references: L<{ author: string; title: string; quote: string }>[] = [
  {
    author: 'Tyler Liu',
    title: 'Meet.jobs, Tech Lead',
    quote:
      'Jarvis is highly skilled in Android related development technology and is proficient in both front-end and back-end web technologies. He prioritizes teamwork and is willing to share his knowledge and information. He is easy to work with and never stops learning, making him an exceptional and ideal partner.',
    // TODO: add zh.author once the Chinese name is decided
    zh: {
      quote:
        'Jarvis 對 Android 相關開發技術非常精熟，同時也擅長前後端網頁技術。他重視團隊合作，樂於分享知識與資訊，好相處又持續學習，是一位出色而理想的夥伴。',
    },
  },
  {
    author: 'Poen Hsiao',
    title: 'COLORFUL STYLE CO., LTD., CTO',
    quote:
      'Jarvis possesses a bold spirit to tackle challenges, think creatively, and continuously seek new knowledge. He showcases remarkable coding skills and possesses a strong team-oriented mentality.',
    zh: {
      title: 'COLORFUL STYLE CO., LTD.，技術長',
      quote:
        'Jarvis 具有勇於面對挑戰的精神，富有創意，並且持續追求新知。他展現出卓越的程式能力，也具備強烈的團隊導向心態。',
    },
  },
  {
    author: 'I-Ching Hsu',
    title: 'National Formosa University, Professor',
    quote: 'Jarvis studied carefully, with the ability to solve problems.',
    zh: {
      title: '國立虎尾科技大學，教授',
      quote: 'Jarvis 求學認真，具備解決問題的能力。',
    },
  },
];
