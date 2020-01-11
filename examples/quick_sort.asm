;PROGRAM SORTUJĄCY TABLICĘ METODĄ QUICK SORT
;https://www.geeksforgeeks.org/quick-sort/
MAXVALUE:   DC      INTEGER(100000)
TAB:        DS      16000*INTEGER
TAB_SIZE:   DC      INTEGER(16000)
SEPARATOR:  DC      CHAR('\n')

MAIN:
            LDA     0, TAB          ;r0 - adres tablicy
            LD      1, TAB_SIZE     ;r1 - rozmiar tablicy
            LD      2, MAXVALUE
            NEG     2               ;r2 - minimalna możliwa wygenerowana wartość(-maxvalue)
            LD      3, MAXVALUE     ;r3 - maksymalna -||-
            CALL    INIT_TAB        ;generowanie wartości z przedziału <r2, r3) w tablicy r0 o rozmiarze r1
            CALL    QUICK_SORT      ;wywołanie sortowania tablicy
            LDB     2, SEPARATOR    ;r2 - separator, którym wypisywane wartości będą oddzielane
            CALL    PRINT_TAB       ;wypisywanie tablicy
            EXIT                    ;wyjście z programu
;INICJALIZUJE TABLICĘ WARTOŚCIAMI OD MINVALUE(INCLUSIVE) DO MAXVALUE(EXLUSIVE)
;ARGUMENTY:
;REJESTR 0 - TAB POINTER
;REJESTR 1 - TAB_SIZE
;REJESTR 2 - MINVALUE
;REJESTR 3 - MAXVALUE
;OPERUJE NA:
;REJESTRY 1, 2, 4, 5
;WYMAGA DEKLARACJI:
;---
;ZWRACA:
;REJESTR 0 - WEJŚCIOWY POINTER NA TAB
;REJESTR 1 - WEJŚCIOWY ROZMIAR TABLICY
INIT_TAB:
            PUSH    0
            PUSH    1
            LD      4, 3
            SUB     4, 2
L1:         RAND    5
            DIV     5, 4
            LD      5, 8
            ADD     5, 2
            ST      5, 0(0)
            LDA     0, 0(4)
            LOOP    1, L1
            POP     1
            POP     0
            RET

;WYŚWIETLA ZAWARTOŚĆ TABLICY
;ARGUMENTY:
;REJESTR 0 - TAB POINTER
;REJESTR 1 - TAB_SIZE
;REJESTR 2 - SEPARATOR
;OPERUJE NA:
;REJESTRY 0, 1
;WYMAGA DEKLARACJI:
;---
;ZWRACA:
;REJESTR 0 - WEJŚCIOWY POINTER NA TAB
;REJESTR 1 - WEJŚCIOWY ROZMIAR TABLICY
PRINT_TAB:
            PUSH    0
            PUSH    1
L2:         IOUT    0(0)
            COUT    2
            LDA     0, 0(4)
            LOOP    1, L2
            POP     1
            POP     0
            RET


;SORTUJĘ TABLICĘ - METODĄ QUICK SORT
;ARGUMENTY:
;REJESTR 0 - TAB POINTER
;REJESTR 1 - TAB_SIZE
;OPERUJE NA:
;REJESTRY 2, 3, 4, 5, 6, 7, 8
;WYMAGA DEKLARACJI:
;---
;ZWRACA:
;REJESTR 0 - WEJŚCIOWY POINTER NA TAB
;REJESTR 1 - WEJŚCIOWY ROZMIAR TABLICY
QUICK_SORT:
            PUSH    0               ;Zapamiętaj wejściowy pointer na stosie,
            PUSH    1               ;to samo rozmiar tablicy
            ENTER
            XOR     8, 8
            DEC     1
            PUSH    1               ;push n-1
            PUSH    8               ;push 0, czyli dwa argumenty wywołania rekurencyjnego,
            INC     8               ;znaczące posortuj tablicę od indeksu 0 do n-1
            INC     8
            ADD     8, 8            ;r8 = 4 - stała wartość
            CALL    QUICK_SORT_REK  ;posortuj tablicę od 0 do n-1
            LEAVE                   ;przywróć stos do stanu z wywołania ENTER
            POP     1               ;Przywróć wejściowy rozmiar tablicy
            POP     0               ;i wskaźnik na nią
            RET



;/* low  --> Starting index,  high  --> Ending index */
;quickSort(arr[], low, high)
;{
;    if (low < high)
;    {
;        /* pi is partitioning index, arr[pi] is now
;           at right place */
;        pi = partition(arr, low, high);
;
;        quickSort(arr, low, pi - 1);  // Before pi
;        quickSort(arr, pi + 1, high); // After pi
;    }
;}

;WYWOŁANIE REKURENCYJNE
;ARGUMENTY:
;STOS(4) - LOW
;STOS(8) - HIGH
;REJESTR 0 - TAB POINTER
;OPERUJE NA:
;REJESTRY 2, 3, 4, 5, 6, 7
;WYMAGA DEKLARACJI:
;---
;ZWRACA:
;REJESTR 0 - WEJŚCIOWY POINTER NA TAB
QUICK_SORT_REK:
            LD      2, 11(4)        ;r2 = low
            CMP     2, 11(8)        ;r2 ? high
            JGE     RETURN          ;r2 >=? high -> goto RETURN
            CALL    PARTITION       ;r1 = partition(arr, low. high)
            DEC     1               ;pi = pi - 1
            PUSH    1               ;push pi - 1
            PUSH    11(8)           ;push low, wepchnięcie dwóch argumentów rekurencji na stos
            CALL    QUICK_SORT_REK  ;quicksort(arr, low, pi-1)
            POP     2
            POP     2               ;r2 = pi, zdjęcie dwóch argumentów rekurencji ze stosu
            INC     2               ;pi = pi + 1
            PUSH    11(8)           ;push high
            PUSH    2               ;push pi + 1
            CALL    QUICK_SORT_REK  ;quicksort(arr, pi+1, high)
            POP     2
            POP     2               ;zdjęcie dwóch argumentów rekurencji ze stosu
RETURN:
            RET



;/* This function takes last element as pivot, places
;   the pivot element at its correct position in sorted
;    array, and places all smaller (smaller than pivot)
;   to left of pivot and all greater elements to right
;   of pivot */
;partition (arr[], low, high)
;{
;    // pivot (Element to be placed at right position)
;    pivot = arr[high];
;
;    i = (low - 1)  // Index of smaller element;
;
;    for (j = low; j < high; j++)
;    {
;        // If current element is smaller than the pivot
;        if (arr[j] < pivot)
;        {
;            i++;    // increment index of smaller element
;            swap arr[i] and arr[j]
;        }
;    }
;    swap arr[i + 1] and arr[high])
;    return (i + 1)
;}

;PARTITION WEDŁUG TAB[HIGH]
;ARGUMENTY:
;STOS(4) - LOW
;STOS(8) - HIGH
;REJESTR 0 - TAB POINTER
;OPERUJE NA:
;REJESTRY 2, 3, 4, 5, 6, 7
;WYMAGA DEKLARACJI:
;---
;ZWRACA:
;REJESTR 0 - WEJŚCIOWY POINTER NA TAB
;REJESTR 1 - PIVOT INDEX
PARTITION:
            LD      2, 11(12)
            LD      4, 2            ;r4 = HIGH
            MUL     2, 8
            ADD     2, 0
            LD      2, 2(0)         ;r2 = PIVOT = tab[HIGH]
            LD      1, 11(8)        ;r1 = LOW
            LD      3, 4            ;j = HIGH
            SUB     3, 1            ;j = HIGH - LOW, j zmniejsza się wraz z obrotem pętli!
            LD      5, 1
            MUL     5, 8
            ADD     5, 0            ;r5 = &tab[LOW]
            DEC     1               ;r1 = i = LOW - 1
            LD      7, 1
            MUL     7, 8
            ADD     7, 0            ;r7 = &tab[i]
L3:
            LD      6, 5(0)         ;r5 = tab[j]
            CMP     6, 2            ;tab[j] ? PIVOT
            JG      CONTINUE        ;tab[j] >? PIVOT -> goto CONTINUE
            INC     1               ;i++
            LDA     7, 7(4)         ;r7 += 4
            XCHG    6, 7(0)         ;r5 <=> tab[i]
            ST      6, 5(0)         ;tab[j] = r5
CONTINUE:
            LDA     5, 5(4)         ;r5 += 4
            LOOP    3, L3           ;j--, goto L3 jeżeli j nieujemne
L3STOP:
            INC     1               ;i++
            LDA     7, 7(4)         ;r7 += 4
            LD      6, 7(0)         ;r6 = tab[i+1]
            MUL     4, 8
            ADD     4, 0
            XCHG    6, 4(0)         ;tab[HIGH] <=> r6
            ST      6, 7(0)         ;tab[i+1] = r6
            RET