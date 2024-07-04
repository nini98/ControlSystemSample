const getKoreanTime = () => {
    const now = new Date();
    const utcTime = now.getTime() + (now.getTimezoneOffset() * 60000);
    const koreaTimeOffset = 9 * 60 * 60000; // 한국 시간 오프셋 (UTC+9)
    return new Date(utcTime + koreaTimeOffset);
};