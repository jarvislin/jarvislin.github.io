export const profile = {
  name: 'Jarvis Lin',
  title: 'Founder · Software Engineer',
  email: 'admin@jarvislin.com',
  location: 'Taipei, Taiwan',
  education: 'National Formosa University, Bachelor, Computer Science and Information Engineering',
  avatar: '/images/avatar_16_9.webp',
  resumeUrl: 'https://drive.google.com/file/d/19rTCemHp7vG50n-AOHgcrF5jJC7Vsuii/view?usp=sharing',
  links: [
    { label: 'Homepage', url: 'https://jarvislin.com', icon: 'home' },
    { label: 'GitHub', url: 'https://github.com/jarvislin', icon: 'github' },
    { label: 'LinkedIn', url: 'https://linkedin.com/in/jarvislin', icon: 'linkedin' },
  ],
};

export const introText =
  'Founder of JINNO, building LingoWave — an AI-powered communication app for Taiwanese care families and migrant caregivers. Software engineer with 14+ years of experience shipping products from zero to launch.';

export const aboutPoints = [
  { description: 'Specialized in taking products from concept to launch, including defining specs and analyzing metrics.', icon: 'barchart' },
  { description: 'Thrives in ambiguity — defining scope from scratch and shipping end-to-end without a playbook.', icon: 'chat' },
  { description: 'Delivered 20+ app products, including those with 100M+ downloads and Editor\'s Choice awards.', icon: 'category' },
  { description: 'Led engineering and product teams across Taiwan and Indonesia.', icon: 'group' },
];

export const skills = [
  { label: 'Fullstack Development', level: 0.9 },
  { label: 'Mobile Development', level: 1.0 },
  { label: 'Scalable Architecture', level: 0.9 },
  { label: 'CI/CD Automation', level: 0.9 },
  { label: 'Cross-functional Collaboration', level: 1.0 },
  { label: 'Product Strategy', level: 0.8 },
  { label: 'Analytics Integration', level: 0.85 },
  { label: 'UI/UX Collaboration', level: 0.9 },
  { label: 'Location-Based Services', level: 0.85 },
  { label: 'Project Management', level: 0.85 },
];

export const achievements = [
  {
    id: 'appstore',
    title: 'Ahead of the AI Giants',
    description: "May 2026 — Crops Price Checker reached #1 on the App Store's free Productivity chart, ahead of Google Gemini, ChatGPT, and Claude.",
    image: '/images/appstore_top.webp',
    paragraphs: [
      "In May 2026, Crops Price Checker — my app showing current and historical fruit and vegetable prices from Taiwan's Council of Agriculture open data — reached #1 on the App Store's free Productivity chart.",
      "It ranked above Google Gemini, ChatGPT, and Claude: a small utility built on public data, ahead of the biggest AI brands on the chart.",
    ],
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
  },
];

export type ProjectCategory = 'All' | 'Work' | 'Side';

export interface ProjectLink {
  title: string;
  type: 'GitHub' | 'PlayStore' | 'AppStore' | 'Website';
  url: string;
}

export interface Project {
  title: string;
  description: string;
  tags: string[];
  category: 'Work' | 'Side';
  logo: string;
  links: ProjectLink[];
  screenshots: string[];
  featured?: boolean;
}

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
  },
  {
    title: 'KMP Hub',
    featured: true,
    category: 'Side',
    logo: '/images/kmp_hub.webp',
    tags: ['Web', 'Compose Multiplatform', 'Kotlin'],
    links: [{ title: 'GitHub', type: 'GitHub', url: 'https://github.com/KMP-Hub' }],
    screenshots: [],
    description: 'KMP Hub is a curated platform for discovering Kotlin Multiplatform libraries and tools. We aim to help developers explore the KMP ecosystem, accelerate cross-platform development, and foster community collaboration.',
  },
  {
    title: 'Hacker News KMP',
    featured: true,
    category: 'Side',
    logo: '/images/hackernews.webp',
    tags: ['App', 'Android', 'iOS', 'Compose Multiplatform', 'Kotlin'],
    links: [{ title: 'GitHub', type: 'GitHub', url: 'https://github.com/jarvislin/HackerNews-KMP' }],
    screenshots: [],
    description: 'This app is an open-source project, designed to showcase the capabilities of Kotlin Multiplatform Compose, delivering a seamless reading experience across Android and iOS platforms.',
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
  },
  {
    title: 'Tumblr',
    featured: true,
    category: 'Work',
    logo: '/images/tumblr.webp',
    tags: ['App', 'Android', 'Kotlin'],
    links: [{ title: 'Google Play', type: 'PlayStore', url: 'https://play.google.com/store/apps/details?id=com.tumblr' }],
    screenshots: [],
    description: 'Tumblr is a microblogging and social networking app where users can post multimedia content and follow other users\' blogs.',
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
  },
  {
    title: 'Life in Taiwan',
    category: 'Side',
    logo: '/images/life.webp',
    tags: ['App', 'Android', 'Kotlin'],
    links: [],
    screenshots: ['/images/screenshot_life_1.webp', '/images/screenshot_life_2.webp', '/images/screenshot_life_3.webp'],
    description: 'A comprehensive science education app that provides detailed information on various species, including animals, plants, and insects.',
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
  },
  {
    title: 'Adoptly',
    category: 'Side',
    logo: '/images/adoptly.webp',
    tags: ['App', 'Android', 'Kotlin'],
    links: [],
    screenshots: ['/images/screenshot_adoptly_1.webp', '/images/screenshot_adoptly_2.webp'],
    description: 'Adoptly provides a way to adopt animals which live in shelters. It also shows information about shelters.',
  },
  {
    title: 'IndoChat',
    featured: true,
    category: 'Work',
    logo: '/images/indochat.webp',
    tags: ['App', 'Android', 'Kotlin'],
    links: [{ title: 'Google Play', type: 'PlayStore', url: 'https://play.google.com/store/apps/details?id=id.co.indochat.app' }],
    screenshots: [],
    description: 'A comprehensive mobile application for the Indonesian government featuring chat, video/audio calling, social media integration, news feeds, and invoice scanning.',
  },
  {
    title: 'Autopass',
    category: 'Work',
    logo: '/images/autopass.webp',
    tags: ['App', 'Android', 'Kotlin'],
    links: [{ title: 'Google Play', type: 'PlayStore', url: 'https://play.google.com/store/apps/details?id=com.pklotcorp.autopass' }],
    screenshots: [],
    description: 'A location-based platform offering a range of services for drivers, including finding parking and refueling options.',
  },
  {
    title: 'PKLOT App',
    featured: true,
    category: 'Work',
    logo: '/images/pklotapp.webp',
    tags: ['App', 'Android', 'Kotlin'],
    links: [{ title: 'Google Play', type: 'PlayStore', url: 'https://play.google.com/store/apps/details?id=com.alfred.parkinglot' }],
    screenshots: [],
    description: 'A parking and refueling assistant app recognized as one of the best apps on Google Play in Taiwan in both 2016 and 2017.',
  },
  {
    title: 'Calories 100',
    category: 'Work',
    logo: '/images/calories100.webp',
    tags: ['App', 'Android', 'Kotlin'],
    links: [],
    screenshots: ['/images/screenshot_calories_1.webp', '/images/screenshot_calories_2.webp', '/images/screenshot_calories_3.webp'],
    description: 'An app for calculating calorie content in foods with social media features integrated with Facebook API.',
  },
  {
    title: 'Crops Price Checker',
    category: 'Side',
    logo: '/images/crops_price_checker.webp',
    tags: ['App', 'Android', 'iOS', 'Compose Multiplatform', 'Kotlin'],
    links: [
      { title: 'Google Play', type: 'PlayStore', url: 'https://play.google.com/store/apps/details?id=com.jarvislin.producepricechecker' },
      { title: 'App Store', type: 'AppStore', url: 'https://apps.apple.com/us/app/id6661027883' },
    ],
    screenshots: [],
    description: "A tool that retrieves open data from Taiwan's Council of Agriculture to display current and historical prices of fruits and vegetables.",
  },
  {
    title: 'Water Restriction',
    category: 'Side',
    logo: '/images/water_rectriction_info.webp',
    tags: ['App', 'Android', 'Java'],
    links: [],
    screenshots: ['/images/screenshot_water_1.webp', '/images/screenshot_water_2.webp'],
    description: "An app that fetches water restriction information from the government during Taiwan's serious drought in 2016, also showing reservoir water levels.",
  },
  {
    title: 'Car Finder',
    category: 'Side',
    logo: '/images/car_finder.webp',
    tags: ['App', 'Android', 'Kotlin'],
    links: [{ title: 'Google Play', type: 'PlayStore', url: 'https://play.google.com/store/apps/details?id=com.jarvislin.carfinder' }],
    screenshots: [],
    description: 'Car Finder uses GPS to detect and record your parking location with photos, plus navigation to help you find your car easily.',
  },
  {
    title: 'Baby Formula',
    category: 'Side',
    logo: '/images/baby_formula.webp',
    tags: ['App', 'Backend', 'Android', 'Java', 'PHP'],
    links: [],
    screenshots: ['/images/screenshot_babyformula_1.webp', '/images/screenshot_babyformula_2.webp', '/images/screenshot_babyformula_3.webp'],
    description: "Developed for a programming contest by Taiwan's Food and Drug Administration, using a milk formula dataset approved for sale in Taiwan.",
  },
  {
    title: 'Small Trade',
    category: 'Work',
    logo: '/images/small_trade.webp',
    tags: ['App', 'Android', 'Java'],
    links: [],
    screenshots: ['/images/screenshot_smalltrade_1.webp', '/images/screenshot_smalltrade_2.webp'],
    description: "A C2C platform where sellers can post products online, upload pictures and videos to make their products authentic.",
  },
  {
    title: 'VR for 3D',
    category: 'Work',
    logo: '/images/vr_for_3d.webp',
    tags: ['App', 'Android', 'Java'],
    links: [],
    screenshots: ['/images/screenshot_vrfor3d_1.webp', '/images/screenshot_vrfor3d_2.webp', '/images/screenshot_vrfor3d_3.webp'],
    description: "A 3D model viewer designed to showcase models created with Ortery's hardware.",
  },
];

export const workExperiences = [
  {
    duration: 'Nov 2024 - Present',
    title: 'Founder & CEO',
    company: 'JINNO',
    description:
      'Defined and executed the company\'s vision, mission, and long-term strategy.\n\nBuilt and launched LingoWave, an AI-powered communication app for Taiwanese long-term care families and migrant caregivers — real-time translation in 6 languages, shared health records, and automated task reminders.\n\nPartnering with the Health Education Center at Chiayi Christian Hospital, Tzu Chi University, and NGOs.\n\nEngaged directly with enterprise clients to identify pain points and deliver custom solutions.',
  },
  {
    duration: 'Nov 2022 - Jun 2024',
    title: 'Mobile Engineer',
    company: 'Automattic',
    description:
      'Developed and implemented new features for the Android applications of Tumblr, WordPress, and Jetpack, including improvements to user experience, performance, and security.\n\nCollaborated with cross-functional teams to troubleshoot and resolve technical and product issues.',
  },
  {
    duration: 'Dec 2021 - Nov 2022',
    title: 'Co-Founder & Technical Director',
    company: 'QTCOMM',
    description:
      'Led engineers to identify root causes and implement scalable technical fixes.\n\nStreamlined IT issue resolution across classified and unclassified systems, lowering infra complexity and cutting stack costs.\n\nRe-architected audio/video call features, replacing the previous solution and cutting its cost by over 85% — saving the company 8%+ of its annual expenses.\n\nSpearheaded integration with external partners, successfully connecting 3+ platforms including Tokopedia and Juiker.',
  },
  {
    duration: 'Nov 2020 - Dec 2021',
    title: 'Project Technical Director',
    company: 'QTCOMM / PT. ICE Messenger Indonesia',
    description:
      'Supervised teams across Taiwan and Indonesia, streamlining workflows based on team feedback—improving efficiency by 40%+.\n\nLed end-to-end product development, delivering mobile app updates, data encryption features, QA testing, and critical bug fixes.\n\nOwned the IndoChat product roadmap from concept to launch.\n\nBuilt event tracking systems; iterated sign-up flow to achieve 70%+ conversion rate.',
  },
  {
    duration: 'Jun 2019 - Nov 2020',
    title: 'Android Team Leader',
    company: 'QTCOMM / PT. ICE Messenger Indonesia',
    description:
      'Led Android engineers to deliver features across multiple apps, refining architecture and product design based on evolving specs.\n\nOversaw end-to-end development of 3+ Android apps—from research and planning to launch and post-release support.\n\nBuilt CI/CD pipelines to automate testing and deployment, saving over 50 minutes of manual work per build.',
  },
  {
    duration: 'Jan 2018 - May 2019',
    title: 'Android Engineer',
    company: 'Autopass',
    description:
      'Developed a location-based Android application with robust architectures and complex user interfaces for parking, gas stations, and driver-related services.\n\nProvided code maintenance and system upgrades to maximize performance.',
  },
  {
    duration: 'Apr 2016 - Dec 2017',
    title: 'Android Engineer',
    company: 'PKLOT (Acquired by Acer)',
    description:
      'Designed and implemented services, applications, and frameworks to power core tech integrations.\n\nBuilt software for license plate recognition, enabling seamless fuel payment matching at gas stations.',
  },
  {
    duration: 'Apr 2015 - Jan 2016',
    title: 'Android Engineer',
    company: 'iFit',
    description:
      'Built advanced fitness applications and SDK for the Android platform, launching new features to create health and fitness experiences.\n\nPrototyped innovative features using the newest APIs; tested for robustness and general reliability.',
  },
  {
    duration: 'Oct 2014 - Nov 2014',
    title: 'Android Engineer',
    company: 'Zinwell',
    description:
      "Developed a proof of concept for Zinwell's e-commerce app, Small Trade, in collaboration with UI designers and engineers.",
  },
  {
    duration: 'Jun 2012 - Jul 2014',
    title: 'Software Engineer',
    company: 'Ortery Technologies, Inc',
    description:
      'Participated in the development of mobile apps and websites.\n\nContributed to the implementation of Scen3D and SaaSPhoto, focusing on front-end and integration tasks.',
  },
];

export const references = [
  {
    author: 'Tyler Liu',
    title: 'Meet.jobs, Tech Lead',
    quote:
      'Jarvis is highly skilled in Android related development technology and is proficient in both front-end and back-end web technologies. He prioritizes teamwork and is willing to share his knowledge and information. He is easy to work with and never stops learning, making him an exceptional and ideal partner.',
  },
  {
    author: 'Poen Hsiao',
    title: 'COLORFUL STYLE CO., LTD., CTO',
    quote:
      'Jarvis possesses a bold spirit to tackle challenges, think creatively, and continuously seek new knowledge. He showcases remarkable coding skills and possesses a strong team-oriented mentality.',
  },
  {
    author: 'I-Ching Hsu',
    title: 'National Formosa University, Professor',
    quote: 'Jarvis studied carefully, with the ability to solve problems.',
  },
];
