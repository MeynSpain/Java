#include <iostream>
using namespace std;
int main()
{
	float c = 3;
	float chet = 4;
	__asm {
	finit
	fld c
	fmul st(0), st(0)
	fldpi
	fdivp st(1), st(0)
	fld chet
	fdivp st(1), st(0)
	fst c
	}
	cout << c;
}