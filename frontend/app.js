document.addEventListener("DOMContentLoaded", function () {
  const video = document.getElementById("video");
  const hls = new Hls();

  if (Hls.isSupported()) {
    const hlsPlaylistURL = "http://localhost:8080/video/stream";

    hls.loadSource(hlsPlaylistURL);
    hls.attachMedia(video);
    hls.on(Hls.Events.MANIFEST_PARSED, function () {
      video.play();
    });
  }
});
