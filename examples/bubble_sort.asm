;PROGRAM SORTUJĄCY TABLICĘ METODĄ BUBBLE SORT
MAXVALUE:   DC      INTEGER(100000)
TAB:        DS      16000*INTEGER
TAB_SIZE:   DC      INTEGER(16000)
SEPARATOR:  DC      CHAR('\n')

MAIN:
            LDA     0, TAB
            LD      1, TAB_SIZE
            LD      2, MAXVALUE
            NEG     2
            LD      3, MAXVALUE
            CALL    INIT_TAB
            CALL    BUBBLE_SORT
            LDB     2, SEPARATOR
            CALL    PRINT_TAB
            EXIT

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

;SORTUJĘ TABLICĘ - METODĄ BUBBLE SORT
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
BUBBLE_SORT:
            PUSH    0
            PUSH    1
            LD      3, 1
L3:
            LD      2, 0
            LDA     4, 2(4)
            LD      5, 3
            DEC     5
            JZ      STOP
L4:
            LD      6, 2(0)
            CMP     6, 4(0)
            JL      SKIP_SWAP
            XCHG    6, 4(0)
            XCHG    6, 2(0)
SKIP_SWAP:
            LDA     4, 4(4)
            LDA     2, 2(4)
            LOOP    5, L4
            LOOP    3, L3
STOP:
            POP     1
            POP     0
            RET