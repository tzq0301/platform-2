import subprocess

command = ["python3", "main.py"]
output_file = "log"

with open(output_file, 'w') as file:
    subprocess.run(command, stdout=file, stderr=subprocess.STDOUT)
