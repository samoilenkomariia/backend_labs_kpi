# Labs for backend course by student of IM-32 Samoilenko Mariia
## Lab 1
1. to run locally you can do it with python:
    1) install python,
    2) create virtual environment
        ```bash
        python3 -m venv env
        ```
        then activate it
        ```bash
        source ./env/bin/activate
        ```
    3) install all dependcies
        ```bash
        pip install -r requirements.txt
        ```
    4) run application
        ```bash
        flask run --host 0.0.0.0 -p 5000
        ```
    5) open http://127.0.0.1:5000/healthcheck
    you will see a message with OK status like this
    ```json
        {
            "current_time": "2025-10-12T15:58:38.002608",
            "service": "My Flask Service",
            "status": "OK"
        }
    ```
2. or you may run it using docker
    1) have docker engine installed
    2) run
        ```bash
        sudo docker build . -t backend_lab1:latest
        sudo docker run -it --rm --network=host -e PORT=5000 backend_lab1:latest
        ```
    3) open http://127.0.0.1:5000/healthcheck
    you will see a message with OK status like this
    ```json
        {
            "current_time": "2025-10-12T15:58:38.002608",
            "service": "My Flask Service",
            "status": "OK"
        }
    ```
    4) or using docker compose
    ```bash
    sudo docker compose build
    sudo docker compose up
    ```

3. Also you can see deployed version here https://backend-labs-kpi.onrender.com/healthcheck
    you will see a message with OK status like this
    ```json
        {
            "current_time": "2025-10-12T15:58:38.002608",
            "service": "My Flask Service",
            "status": "OK"
        }
    ```
