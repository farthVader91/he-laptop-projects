#include <stdio.h>

int main() {
  FILE *fp;
  int i;
  fp = fopen("../filep.c", "r");
  i = fileno(fp);
  printf("%d\n", i);
  return 0;
}
