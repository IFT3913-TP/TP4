\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{rapport-tp-qlm}[2022/10/28]

\DeclareOption*{\PassOptionsToClass{\CurrentOption}{report}}
\ProcessOptions\relax

\LoadClass[notitlepage]{report}

\setcounter{secnumdepth}{3}
\setcounter{tocdepth}{3}


\RequirePackage[a4paper,margin=2cm]{geometry} % définit le format de la page

\RequirePackage[french]{babel}  % Pour une typographie «à la Française»
\AtBeginDocument{\renewcommand\labelitemi{\textbullet}}

\renewcommand{\thechapter}{\Roman{chapter}}

\RequirePackage{varwidth}

\def\@makechapterhead#1{{\parindent \z@ \raggedright \normalfont \LARGE \bfseries
	\framebox[\linewidth][c]{\begin{varwidth}{.95\linewidth}\interlinepenalty\@M
		\ifnum \c@secnumdepth >\m@ne \thechapter \space --\space \fi
    #1\par\nobreak
		\end{varwidth}}
		\vskip 40\p@ }}

\def\@makeschapterhead#1{{\parindent \z@ \raggedright \normalfont \LARGE \bfseries
	\framebox[\linewidth][c]{\begin{varwidth}{.95\linewidth}\interlinepenalty\@M    
    #1\par\nobreak
		\end{varwidth}}
		\vskip 40\p@ }}

\def\@maketitle{\newpage \null \vskip 2em \begin{center}%
	\let \footnote \thanks
	{\normalfont \LARGE \bfseries \@title \par}%
	\vskip 1.5em%
	{\large \bfseries \lineskip .5em \begin{tabular}[t]{c} \@author \end{tabular}\par}%
	\vskip 1em {\large \@date}%

  \end{center} \par \vskip 1.5em}

\renewenvironment{abstract}
	{\center{\bfseries \abstractname\vspace{-\topsep}}\endcenter\quotation}
	{\endquotation}


\RequirePackage{soul,color}
\newcommand{\surligne}[2][yellow]{\sethlcolor{#1}\hl{#2}}

\renewcommand{\emph}[1]{\textbf{#1}}


\usepackage{etoolbox}
\makeatletter
\patchcmd{\chapter}{\if@openright\cleardoublepage\else\clearpage\fi}{}{}{}
\makeatother