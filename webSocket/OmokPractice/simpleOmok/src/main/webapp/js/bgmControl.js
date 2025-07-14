window.addEventListener("DOMContentLoaded", () => {
    const bgm = document.getElementById("bgm");
    const btn = document.getElementById("playBgmBtn");

    if (btn && bgm) {
        btn.addEventListener("click", () => {
            if (bgm.paused) {
                bgm.play().then(() => {
                    btn.textContent = "🔇 BGM 끄기";
                });
            } else {
                bgm.pause();
                btn.textContent = "🎵 BGM 켜기";
            }
        });
    }
});