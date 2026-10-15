# Resume

Personal resume site built with Astro 4 + Tailwind CSS v3, located in `website/`.

```bash
cd website
npm install
npx astro dev      # http://localhost:4321
npx astro build    # output: website/dist
```

Pushing to `master` triggers `.github/workflows/deploy.yml`, which builds the site and publishes `website/dist` to the `gh-pages` branch.
