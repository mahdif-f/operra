// types/crud.ts

export type FieldType = 'TEXT' | 'INTEGER' | 'DECIMAL' | 'DATE' | 'BOOLEAN' | 'REFERENCE' | 'ENUM' | 'MULTI_LINE_TEXT';


export interface FieldDto {
    name: string;
    label: string;
    type: FieldType;
    required?: boolean;
    readonly?: boolean;
    placeholder?: string;
    defaultValue?: string;
    options?: { label: string; value: any }[]; // برای ENUM
    lookupId?: string; // برای REFERENCE، آیدی lookup تعریف‌شده
}


export interface RuleDto {
    type: string;
    trigger: string;
    targetField: string;
    expression: string;
    message: string;
}

export interface LookupDto {
    id: string;
    title: string;
    query: string;
    columns: string[];
    parameters: string[];
    options?: { label: string; value: any }[]; // حالت اولیه که دیتا embed شده است
}

export interface CrudContextDto {
    title: string;
    formType: string;
    fields: FieldDto[];
    rules?: RuleDto[];
    lookups?: LookupDto[];
}

// ✅ مدل پاسخ برای API لوکاپ
export interface LookupResultDto {
    value: any;
    title: string;
    [key: string]: any;
}
