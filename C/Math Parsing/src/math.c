#include "math.h"


// goal: read equations from a file and write them in a solved state to another
// file
//   NOTE: format details specified in instructions
// param qfile: file with math questions to solve
// param afile: file to write equations with answers in
// example:
//   qfile at start of function:
//     12 + 13
//     24 / 5
//     8 * 234
//     65 - 78
//     239 % 13
//   afile after function:
//      12 +  13 = 25
//      24 /   5 = 4
//       8 * 234 = 1872
//      65 -  78 = -13
//     239 %  13 = 5
//
// TODO: Complete the function
void solve(const char* qfile, const char* afile) {
	FILE* fr;
	FILE* fw;
	fr = fopen(qfile, "r");
	if(fr == NULL){
		return;
	}
	fw = fopen(afile, "w");
	if(fw == NULL){
		fclose(fr);
		return;
	}
	int x = 0;
	char s;
	int y = 0;
	while(fscanf(fr, "%d %c %d\n ", &x, &s, &y) != EOF){
		switch(s){
			case '+':
				fprintf(fw, "%3d + %3d = %d\n", x, y, x + y);
				break;
			case '-':
				fprintf(fw, "%3d - %3d = %d\n", x, y, x - y);
                                break;
			case '*':
				fprintf(fw, "%3d * %3d = %d\n", x, y, x * y);
                                break;
			case '/':
				fprintf(fw, "%3d / %3d = %d\n", x, y, x / y);
                                break;
			case '%':
				fprintf(fw, "%3d %% %3d = %d\n", x, y, x % y);
                                break;
			default:
				printf("ERROR");
		}
	}
	fclose(fr);
	fclose(fw);
	return;
}
