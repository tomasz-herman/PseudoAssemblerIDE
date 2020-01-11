;PROGRAM W STYLU HELLO WORLD
QUESTION:       DC      STRING("What's your name?\n")
HELLO:          DC      STRING("Hello ")
NAME:           DS      21*CHAR
MAX_NAME_LEN:   DC      INTEGER(20)
NEWLINE:        DC      CHAR('\n')

MAIN:
            LDA     0, QUESTION
            CALL    WYPISZ_NAPIS
            LDA     0, NAME
            LD      1, MAX_NAME_LEN
            CALL    WCZYTAJ_IMIE
            LDA     0, HELLO
            CALL    WYPISZ_NAPIS
            LDA     0, NAME
            CALL    WYPISZ_NAPIS
            EXIT

;WYPISUJE NAPIS OD WSKAZANEGO PRZEZ WSKAŹNIK MIEJSCA AŻ DO NAPOTKANEGO ZNAKU \0
;ARGUMENTY:
;REJESTR 0 - WSKAŹNIK NA NAPIS
;OPERUJE NA:
;REJESTR 1, 2
;WYMAGA DEKLARACJI:
;---
;ZWRACA:
;REJESTR 0 - WEJŚCIOWY POINTER NA TAB
WYPISZ_NAPIS:
                PUSH    0
                XOR     1, 1
_LOOP1:         LDB     2, 0(0)
                CMP     2, 1
                JZ      _STOP1
                COUT    2
                LDA     0, 0(1)
                JMP     _LOOP1
_STOP1:
                POP     0
                RET

;WCZYTUJE NAPIS Z KLAWIATURY
;ARGUMENTY:
;REJESTR 0 - WSKAŹNIK NA NAPIS
;REJESTR 1 - MAKSYMALNA DŁUGOŚĆ NAPISU
;OPERUJE NA:
;REJESTR 1, 2, 3
;WYMAGA DEKLARACJI:
;NEWLINE - CHAR('\n')
;ZWRACA:
;REJESTR 0 - WEJŚCIOWY POINTER NA TAB
WCZYTAJ_IMIE:
                PUSH    0
                LDB     3, NEWLINE
_LOOP2:         IN      2
                STB     2, 0(0)
                LDA     0, 0(1)
                CMP     2, 3
                JE      _STOP2
                LOOP    1, _LOOP2
_STOP2:
                XOR     2, 2
                STB     2, 0(0)
                POP     0
                RET