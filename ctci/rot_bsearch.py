# 0 1 2 3 4 5 6
# 3 4 5 6 7 1 2
# 7 1 2 3 4 5 6


def findmin(arr, lo, hi):
    if hi < lo:
        return arr[0]
    if lo == hi:
        return arr[lo]
    mid = lo + (hi - lo) / 2

    if mid > lo and arr[mid] < arr[lo]:
        return arr[mid]

    if arr[hi] > arr[mid]:
        return findmin(arr, lo, mid - 1)
    return findmin(arr, mid + 1, hi)


def main():
    print findmin([3, 4, 5, 6, 7, 1, 2], 0, 6)


main()
