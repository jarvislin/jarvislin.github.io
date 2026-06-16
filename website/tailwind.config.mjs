/** @type {import('tailwindcss').Config} */
export default {
  content: ['./src/**/*.{astro,html,js,ts}'],
  darkMode: ['selector', '[data-theme="dark"]'],
  theme: {
    extend: {
      colors: {
        primary: 'var(--color-primary)',
        'on-primary': 'var(--color-on-primary)',
        'primary-container': 'var(--color-primary-container)',
        secondary: 'var(--color-secondary)',
        'secondary-container': 'var(--color-secondary-container)',
        'on-secondary-container': 'var(--color-on-secondary-container)',
        surface: 'var(--color-surface)',
        'on-surface': 'var(--color-on-surface)',
        'surface-variant': 'var(--color-surface-variant)',
        'on-surface-variant': 'var(--color-on-surface-variant)',
        'surface-container': 'var(--color-surface-container)',
        'surface-container-high': 'var(--color-surface-container-high)',
        'surface-container-highest': 'var(--color-surface-container-highest)',
        'surface-container-low': 'var(--color-surface-container-low)',
        outline: 'var(--color-outline)',
        'outline-variant': 'var(--color-outline-variant)',
        error: 'var(--color-error)',
      },
      maxWidth: {
        content: '840px',
      },
    },
  },
};
