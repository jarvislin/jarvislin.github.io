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
      // MD3 window size classes: compact < 600, medium 600–839, expanded ≥ 840 (only `md:` is used)
      screens: {
        md: '840px',
      },
      // MD3 shape scale
      borderRadius: {
        'shape-xs': 'var(--shape-xs)',
        'shape-sm': 'var(--shape-sm)',
        'shape-md': 'var(--shape-md)',
        'shape-lg': 'var(--shape-lg)',
        'shape-xl': 'var(--shape-xl)',
      },
      // MD3 type scale: [size, { lineHeight, letterSpacing, fontWeight }]
      fontSize: {
        'display-md': ['2.8125rem', { lineHeight: '3.25rem', letterSpacing: '0', fontWeight: '400' }],
        'display-sm': ['2.25rem', { lineHeight: '2.75rem', letterSpacing: '0', fontWeight: '400' }],
        'headline-lg': ['2rem', { lineHeight: '2.5rem', letterSpacing: '0', fontWeight: '400' }],
        'headline-md': ['1.75rem', { lineHeight: '2.25rem', letterSpacing: '0', fontWeight: '400' }],
        'headline-sm': ['1.5rem', { lineHeight: '2rem', letterSpacing: '0', fontWeight: '400' }],
        'title-lg': ['1.375rem', { lineHeight: '1.75rem', letterSpacing: '0', fontWeight: '400' }],
        'title-md': ['1rem', { lineHeight: '1.5rem', letterSpacing: '0.009375rem', fontWeight: '500' }],
        'title-sm': ['0.875rem', { lineHeight: '1.25rem', letterSpacing: '0.00625rem', fontWeight: '500' }],
        'body-lg': ['1rem', { lineHeight: '1.5rem', letterSpacing: '0.03125rem', fontWeight: '400' }],
        'body-md': ['0.875rem', { lineHeight: '1.25rem', letterSpacing: '0.015625rem', fontWeight: '400' }],
        'body-sm': ['0.75rem', { lineHeight: '1rem', letterSpacing: '0.025rem', fontWeight: '400' }],
        'label-lg': ['0.875rem', { lineHeight: '1.25rem', letterSpacing: '0.00625rem', fontWeight: '500' }],
        'label-md': ['0.75rem', { lineHeight: '1rem', letterSpacing: '0.03125rem', fontWeight: '500' }],
      },
    },
  },
};
