import os
from openai import OpenAI
from dotenv import load_dotenv

# .env 읽어오기
load_dotenv()

client = OpenAI(api_key=os.getenv("OPENAI_API_KEY"))   # 발급받은 키 넣기

resp = client.chat.completions.create(
    model="gpt-4o-mini",   # 또는 "gpt-3.5-turbo"
    messages=[
        {"role": "user", "content": "안녕! 키 잘 불러와졌어?"}
    ]
)

print(resp.choices[0].message.content)
