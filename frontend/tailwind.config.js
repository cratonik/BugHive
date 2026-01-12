/** @type {import('tailwindcss').Config} */
export default {
    content: [
        "./index.html",
        "./src/**/*.{js,ts,jsx,tsx}",
    ],
    theme: {
        extend: {
            colors: {
                primary: "#4F73FF",
                secondary: "#22C3A6",
                background: "#F5F7FB",
                sidebar: "#0F172A",
                text: "#0F172A",
                muted: "#64748B",
                danger: "#EF4444",
                success: "#22C55E",
            },
        },
    },
    plugins: [],
};
