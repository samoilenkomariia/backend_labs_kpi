import datetime
from flask import jsonify
from . import app 

@app.route('/healthcheck', methods=['GET'])
def health_check():
    """
    GET endpoint /healthcheck, returns status 200 & JSON with service status and current time
    """    
    response_data = {
        "status": "OK",
        "current_time": datetime.datetime.now().isoformat(),
        "service": "My Flask Service"
    }
    
    return jsonify(response_data), 200