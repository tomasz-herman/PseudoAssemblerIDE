;PROGRAM KONKATENUJĄCY DWA NAPISY.
ALA_MA_KOTA:    DC      STRING("Ala ma kota, ")
KOT_MA_ALE:     DC      STRING("a kot ma ale.\n")
KONKAT_POINTER: DS      INTEGER

MAIN:
                LDA     0, ALA_MA_KOTA
                LDA     1, KOT_MA_ALE
                LDA     2, 9(0)
                ST      2, KONKAT_POINTER
                CALL    KONKATENUJ
                LD      0, KONKAT_POINTER
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

;KONKATENUJE NAPIS A I B
;ARGUMENTY:
;REJESTR 0 - WSKAŹNIK NA NAPIS A
;REJESTR 1 - WSKAŹNIK NA NAPIS B
;REJESTR 2 - WSKAŹNIK NA NAPIS AB
;OPERUJE NA:
;REJESTR 3, 4
;WYMAGA DEKLARACJI:
;---
;ZWRACA:
;REJESTR 0 - WEJŚCIOWY POINTER NA A
;REJESTR 1 - WEJŚCIOWY POINTER NA B
;REJESTR 2 - WEJŚCIOWY POINTER NA AB
;REJESTR 3 - WSKAŹNIK NA NASTĘPNY ZNAK PO NAPISIE AB
KONKATENUJ:
                PUSH    0
                PUSH    1
                PUSH    2
                XOR     3, 3
_LOOP2:         LDB     4, 0(0)     ;PRZEPISZ PIERWSZY NAPIS
                CMP     4, 3
                JZ      _LOOP3
                STB     4, 2(0)
                LDA     0, 0(1)
                LDA     2, 2(1)
                JMP     _LOOP2
_LOOP3:         LDB     4, 1(0)     ;PRZEPISZ DRUGI NAPIS
                CMP     4, 3
                JZ      _STOP2
                STB     4, 2(0)
                LDA     1, 1(1)
                LDA     2, 2(1)
                JMP     _LOOP3
_STOP2:
                STB     3, 2(0)     ;DOPISZ ZNAK '\0'
                LDA     2, 2(1)
                LD      3, 2
                POP     2
                POP     1
                POP     0
                RET