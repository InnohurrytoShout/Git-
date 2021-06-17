#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<sys/socket.h>
#include<sys/types.h>
#include<netinet/in.h>
#include<arpa/inet.h>
#include<unistd.h>


int mathbot(int a, int b, char op){
	switch(op) {

	case '+':
		return a + b;
		break;
	case '-':
		return a - b;
		break;
	case '*':
		return a * b;
		break;
	case '/':
		return a / b;
		break;
	default:
		printf("invalid operation\n");
		exit(EXIT_FAILURE);
	}
}


int main(int argc, char *argv[]){

	if (argc != 4) {
		printf("improper argument");
		exit(EXIT_FAILURE);
	}

	char *id = argv[1];
	int port = atoi(argv[2]);
	char *host_addr = argv[3];

	int client_fd = socket(AF_INET, SOCK_STREAM, 0);
	if (client_fd < 0) {
		exit(EXIT_FAILURE);
	}

	struct in_addr trans_addr;
	if (inet_aton(host_addr, &trans_addr) == 0) {
		printf("invalid ip address");
		exit(EXIT_FAILURE);
	}

	struct sockaddr_in serv_addr= {AF_INET, htons(port), trans_addr, 0};

	ssize_t connection = connect(client_fd, (struct sockaddr*)&serv_addr, sizeof(serv_addr));
	if (connection != 0) {
		printf("Failed to Connect");
		exit(EXIT_FAILURE);
	}else {
		printf("Connected");
	}

	char buf[512];
	sprintf(buf, "cs230 HELLO %s\n", id);
	send(client_fd, buf, strlen(buf), 0);

	int a;
	int b;
	char op;
	while (1) {
		if (recv(client_fd, buf, 512, 0) == -1) {
			printf("Receive error!");
			exit(EXIT_FAILURE);
		}
		if (sscanf(buf, "cs230 STATUS %d %c %d", &a, &op, &b) == 3) {
			sprintf(buf, "cs230 %d\n", mathbot(a, b, op));
			if (send(client_fd, buf, strlen(buf), 0) == -1) {
				printf("Send error!");
				exit(EXIT_FAILURE);
			}
		}
	}

	close(client_fd);

	return 0;
}
