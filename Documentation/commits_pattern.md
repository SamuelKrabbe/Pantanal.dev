# Conventional Commits Pattern

O Conventional Commits Pattern é uma convenção para mensagens de commit que proporciona uma série de benefícios na leitura, compreensão e geração automática de changelogs e versões.

## O que é?

Conventional Commits é um padrão para mensagens de commit que segue uma estrutura definida. Ele visa tornar o histórico de commit mais fácil de ler e entender, permitindo também o uso de ferramentas automáticas para gerar changelogs e determinar tipos de versão (por exemplo, semântico) com base nas mensagens de commit.

A estrutura básica de um commit convencional é:

```
<tipo>[escopo opcional]: <descrição>
```

- **tipo:** Representa o tipo de mudança (e.g., `feat`, `fix`, `chore`, `docs`, `style`, `refactor`, `perf`, `test`).
  
- **escopo (opcional):** Um identificador para indicar o escopo da mudança, como um módulo ou parte do sistema.
  
- **descrição:** Uma descrição curta e clara da mudança.

## Como usar?

Aqui estão alguns exemplos de mensagens de commit convencionais:

- `feat(auth): adicionar verificação de OTP`
- `fix(button): corrigir alinhamento do ícone`
- `chore: atualizar dependências`
- `docs(api): adicionar documentação para o endpoint de usuários`

Quando for introduzida uma mudança que quebra a compatibilidade (breaking change), você pode usar o footer `BREAKING CHANGE:` para descrever o que foi modificado:

```
feat(database): alterar estrutura da tabela users
BREAKING CHANGE: a coluna 'name' foi removida e substituída por 'first_name' e 'last_name'
```

## Vantagens

- **Legibilidade:** Torna o histórico de commits muito mais fácil de ler e entender, especialmente em projetos com muitos colaboradores.
  
- **Automatização:** Permite o uso de ferramentas como `standard-version` ou `semantic-release` para gerar automaticamente changelogs e versões baseadas no histórico de commit.
  
- **Integração com CI/CD:** Pode ser integrado a sistemas de Integração Contínua e Entrega Contínua para criar pipelines de deploy mais inteligentes.
  
- **Comunicação:** Fornece uma estrutura clara para comunicar as mudanças e seu impacto para os colaboradores e usuários do projeto.

- **Consistência:** Ao adotar o padrão, todos os membros da equipe estarão alinhados quanto à forma de documentar as mudanças, tornando o histórico uniforme.

Em resumo, o Conventional Commits Pattern é uma excelente prática para manter um histórico de commits claro, significativo e fácil de seguir, além de permitir a integração com ferramentas que automatizam muitos processos relacionados à gestão de versões e changelogs.
