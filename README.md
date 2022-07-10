# noteService (с изменениями)
### Проигнорированы поля у методов:

#### add
- privacy
- comment_privacy
- privacy_view
- privacy_comment
#### createComment
- owner_id
- reply_to
- guid
#### deleteComment
- owner_id
#### edit
- privacy
- comment_privacy
- privacy_view
- privacy_comment
#### editComment
- owner_id
#### get
- note_ids
- user_id
- offset
- count
- sort
#### getByID
- owner_id
- need_wiki
#### getComments
- owner_id
- sort
- offset
- count
#### restoreComment
- owner_id
### Проигнорирован метод:
- ##### getFriendsNotes
#### Причина :
- Данный метод устарел и может быть отключён через некоторое время, пожалуйста, избегайте его использования.

