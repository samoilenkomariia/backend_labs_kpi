FROM python:3.11.3-slim-bullseye
ENV PORT=5000
WORKDIR /app
COPY requirements.txt .
RUN python -m pip install -r requirements.txt
COPY . /app
CMD flask --app lab1 run --host 0.0.0.0 --port $PORT