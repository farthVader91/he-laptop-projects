#include <stdio.h>

int main() {
  FILE *fp;
  char str[60];
  fp = fopen("read-file.c", "r");
  fgets(str, 50, fp);
  puts(str);
  size_t len = strlen(str);
  printf("%d", len);
  return 0;
}
