import os
from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from dotenv import load_dotenv
from openai import OpenAI

# .env 로드
load_dotenv()

app = FastAPI(title="LLM Recommender")

# OpenAI 클라이언트 초기화
client = OpenAI(api_key=os.getenv("OPENAI_API_KEY"))

# 요청 스키마
class RecommendReq(BaseModel):
    mood: str
    category: str

# 응답 스키마
class RecommendRes(BaseModel):
    summary: str
    recommendation: str

@app.post("/recommend", response_model=RecommendRes)
def recommend(req: RecommendReq):
    try:
        prompt = f"""
        사용자의 현재 감정은 '{req.mood}'이고,
        좋아하는 카테고리는 '{req.category}' 입니다.
        이 사람에게 어울리는 추천 상품을 하나 알려줘
        """

        resp = client.chat.completions.create(
            model="gpt-4o-mini",
            messages=[{"role":"user", "content": prompt}],
            temperature=0.7,
        )

        answer = resp.choices[0].message.content

        return RecommendRes(
            summary=f"감정={req.mood}, 카테고리={req.category}",
            recommendation=answer
        )
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
