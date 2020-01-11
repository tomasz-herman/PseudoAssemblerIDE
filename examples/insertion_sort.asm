;PROGRAM SORTUJĄCY TABLICĘ METODĄ INSERTION SORT
;https://www.geeksforgeeks.org/insertion-sort/
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
            CALL    INSERTION_SORT  ;wywołanie sortowania tablicy
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

;    // Function to sort array
;    // using insertion sort
;    void sort(int[] arr)
;    {
;        int n = arr.Length;
;        for (int i = 1; i < n; ++i) {
;            int key = arr[i];
;            int j = i - 1;
;
;            // Move elements of arr[0..i-1],
;            // that are greater than key,
;            // to one position ahead of
;            // their current position
;            while (j >= 0 && arr[j] > key) {
;                arr[j + 1] = arr[j];
;                j = j - 1;
;            }
;            arr[j + 1] = key;
;        }
;    }
;SORTUJĘ TABLICĘ - METODĄ INSERTION SORT
;ARGUMENTY:
;REJESTR 0 - TAB POINTER
;REJESTR 1 - TAB_SIZE
;OPERUJE NA:
;REJESTRY 2, 3, 4, 5, 6
;WYMAGA DEKLARACJI:
;---
;ZWRACA:
;REJESTR 0 - WEJŚCIOWY POINTER NA TAB
;REJESTR 1 - WEJŚCIOWY ROZMIAR TABLICY
INSERTION_SORT:
            PUSH    0           ;Zapamiętaj wejściowy pointer na stosie,
            PUSH    1           ;to samo rozmiar tablicy
            DEC     1           ;n = n-1 =: i
            XOR     5, 5        ;r5 = 0
                                ;W pierwszej pętli 'i' zaczyna od n - 1, i zmniejsza się!
L3:                             ;Algorytm jest ten sam, ale indeksacja się różni.
            LDA     2, 0(0)     ;r2 = &tab[j]
            LDA     0, 0(4)     ;r0 = &tab[n - i]
            LD      4, 0(0)     ;r4 = &r0 =: key
            LD      3, 11(0)    ;r3 = n
            SUB     3, 1        ;r3 -= i (r3 = n - i =: j)
L4:                             ;'j' bez zmian, tak samo jak w przykładzie
            CMP     3, 5        ;j ? 0
            JL      BREAK       ;j <? 0 -> goto break
            CMP     4, 2(0)     ;key ? tab[j]
            JGE     BREAK       ;key >=? tab[j] -> goto break
            LD      6, 2(0)     ;r6 = tab[j]
            ST      6, 2(4)     ;tab[j+1] = r6
            LDA     2, 2(-4)    ;r2 = tab[j-1]
            LOOP    3, L4       ;j--, goto L4 jeżeli j nieujemne
BREAK:
            ST      4, 2(4)     ;tab[j+1] = key
            LOOP    1, L3       ;i--, goto L3 jeżeli i nieujemne
STOP:
            POP     1           ;Przywróć wejściowy rozmiar tablicy
            POP     0           ;i wskaźnik na nią
            RET