import sad from "./assets/images/sad.png";
import soso from "./assets/images/soso.png";
import sosohappy from "./assets/images/sosohappy.png";
import happy from "./assets/images/happy.png";
import angry from "./assets/images/angry.png";

export const getEmotionImgById = (emotionId) => {
  const targetEmotionId = String(emotionId);
  switch (targetEmotionId) {
    case "1":
      return sosohappy;
    case "2":
      return happy;
    case "3":
      return soso;
    case "4":
      return sad;
    case "5":
      return angry;
    default:
      return null;
  }
};

export const getFormattedDate = (targetDate) => {
  let year = targetDate.getFullYear();
  let month = targetDate.getMonth() + 1;
  let date = targetDate.getDate();
  if (month < 10) {
    month = `0${date}`;
  }

  return `${year}-${month}-${date}`;
};

//감정 이미지 렌더링
export const emotionsList = [
  {
    id: 1,
    name: "완전 좋음",
    img: getEmotionImgById(1),
  },
  {
    id: 2,
    name: "좋음",
    img: getEmotionImgById(2),
  },
  {
    id: 3,
    name: "그럭저럭",
    img: getEmotionImgById(3),
  },
  {
    id: 4,
    name: "나쁨",
    img: getEmotionImgById(4),
  },
  {
    id: 5,
    name: "끔찍함",
    img: getEmotionImgById(5),
  },
];

//getMonthRangeByDate()를 통해 시간을 가져오는 건지..?
//일자는 제거하고 시간을 넣은건가?
export function getMonthRangeByDate() {
  const beginTimeStamp = new Date(
    date.getFullYear(),
    date.getMonth(),
    1
  ).getTime();
  const endTimeStamp = new Date(
    date.getFullYear(),
    date.getMonth() + 1,
    0,
    23,
    59,
    59
  ).getTime();
  return { beginTimeStamp, endTimeStamp };
}
