### How to ssh into the virtual machine
To ssh into the computer, use the folowing command:
"""bash
ssh -i "~/.ssh/board-master" root@165.232.122.236
"""

### How to update the project
"""bash
cd board-master/
git pull
docker compose down
docker compose up --build -d
"""
