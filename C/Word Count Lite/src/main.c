#include "main.h"


// this is a sort of "from-scratch challenge"
// instructions on what the program should do are on the site
// (specifics are a way too involved for a pre-function comment, sorry. -蛇)
int main(int argc, char **argv) {

	//variable for getopt
	int opt;
	char *optstr = ":cwnlo:f:";
	int cc = 0;
	int wc = 0;
	int lc = 0;
	int nc = 0;
	int length = 20;
	FILE *fw;
	fw = stdout;
	while((opt = getopt(argc, argv, optstr)) != -1){
		switch(opt){
		case'c': cc = 1; break;
		case'w': wc = 1; break;
		case'n': nc = 1; break;
		case'l': lc = 1; break;
		case'o':
			fw = fopen(optarg, "w");
			if(fw == NULL){
                		fprintf(stderr, "%s: No such file or directory\n", optarg);
        		}
			break;
		case'f': length = atoi(optarg); break;
		default: cc = wc = lc = 1;
		}
	}
	if(optind < argc && !cc && !wc && !nc && !lc){
		cc = wc = lc = 1;
	}
	length = -length;


	//variable for counting characters, words, lines, numbers and total.
	FILE *fr;
	int chara = 0;
	int word = 0;
	int line = 0;
	int number = 0;
	int ct = 0;
	int wt = 0;
	int lt = 0;
	int nt = 0;
	int v = 0;
	char *w;
	char *t;
	//variable for getline.
	char *buffer = NULL;
	size_t bufsize = 0;
	ssize_t lines;


	for(int i = optind; i < argc; ++i){
		fr = fopen(argv[i],"r");
		chara = word = line = number = 0;
		if(fr == NULL){
			fprintf(stderr, "%s: No such file or directory\n", argv[i]);
		}else{
			while((lines = getline(&buffer, &bufsize, fr)) != -1){
				++line;
				chara += strlen(buffer);
				t = strdup(buffer);
				while((w = strtok(t, " :\n")) != NULL){
                                	++word;
					v = 0;
					while(v < strlen(w)){
						if(strspn(w + v, "0123456789") != 0){
							++number;
							v += strspn(w + v,"0123456789");
						}else{
							v += strcspn(w + v,"0123456789");
						}
					}
                                	t = NULL;
                        	}
			}
			free(buffer);
			ct += chara;
                        wt += word;
			lt += line;
			nt += number;

			fprintf(fw, "%*s:", length, argv[i]);
			if(cc == 1){ fprintf(fw, "%6d", chara);}
			if(wc == 1){ fprintf(fw, "%6d", word);}
			if(nc == 1){ fprintf(fw, "%6d", number);}
			if(lc == 1){ fprintf(fw, "%6d", line);}
			fprintf(fw, "\n");
			fclose(fr);
		}
	}

	fprintf(fw, "%*s:", length, "total");
        if(cc == 1){ fprintf(fw, "%6d", ct);}
        if(wc == 1){ fprintf(fw, "%6d", wt);}
        if(nc == 1){ fprintf(fw, "%6d", nt);}
	if(lc == 1){ fprintf(fw, "%6d", lt);}
	fprintf(fw, "\n");
	if(fw != stdout){fclose(fw);}
	return 0;
}
